package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/**
 */
class RaiffeisenAccountInfoScrapper implements AccountInfoScraper {

    private static final String BANK_NAME = "Raiffeisen Bank"
    private static final String BANK_CODE = "5550"

    private String url
    private String html

    private static final String RE_AMOUNT_MATCH = /.*\d*\.\d*<br.*/
    private static final String RE_AMOUNT_SELECT = /(\d*\.\d*)<br.*/
    private static final String RE_ACCOUNT_NUMBER_SELECT = /(\d*)<\/h2>/


    @Override
    Account getAccount() {
        def account = new Account()
        account.bank = BANK_NAME;

        Document document

        if (url && html) throw new RuntimeException(
                "You defined both url (${url}) and html as source for scrapper." +
                "I don't know which to choose. Please fill only one.")

        if (url) {
           document = Jsoup.connect(url.toString()).get()
        } else if (html) {
           document = Jsoup.parse(html)
        } else {
            throw new RuntimeException("No source of html to be scraped.")
        }

        account.number = (document.select("div#page div#text2 div#prava-cast h2")[0] =~ RE_ACCOUNT_NUMBER_SELECT)[0][1] + "/${BANK_CODE}"
        account.balance = new BigDecimal((document.select("div#page div#text2 div#prava-cast p strong")[1] =~ /(\d*.\d*) CZK<\/strong>/)[0][1])

        def rows = document.select("div#page div#text2 div#prava-cast table tbody tr")
        account.items = []
        account.totalSpend = 0
        account.totalIncome = 0
        rows.each {row ->
            def item = parseRow(row)
            if (item != null) {
                item.account = account
                account.items << item
                if (item.amount > 0) {
                    account.totalIncome += item.amount
                } else {
                    account.totalSpend += item.amount
                }
            }
        }
        log.debug "Scraped ${account.items.size()} items."

        return account
    }

    /**
     * <code>
     *     <tr>
            <td>2012-10-21 22:50:16.0<br />2012-10-21 22:50:16.0<br /></td>
            <td class="whitelc"> <br />Jan Fischer 2013</td>
            <td class="whitel">2012-10-21 22:50:16.0<br />2012-10-21 22:50:16.0<br /> Převod</td>
            <td class="whitel"> <br /> <br /> </td>
            <td class="whitep">1559200.00<br /></td>
            <td class="whtransaction">-5.00<br /><br /></td>
     </tr>
     * </code>
     * @param e
     * @return
     */
    def TransactionItem parseRow(Element e) {
        TransactionItem item = new TransactionItem()
        try {
            if (e.select("td")[4] ==~ RE_AMOUNT_MATCH) {
                item.amount = new BigDecimal((e.select("td")[4] =~ RE_AMOUNT_SELECT)[0][1])
            } else {
                return null
            }
        } catch (Exception exception) {
            log.error("Error scraping row: ${e}", exception)
            throw exception
        }
        return item
    }
}
