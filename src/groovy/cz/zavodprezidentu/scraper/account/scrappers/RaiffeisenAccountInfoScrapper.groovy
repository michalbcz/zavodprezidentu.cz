package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import cz.zavodprezidentu.utils.Consts
import org.apache.commons.logging.LogFactory
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.util.Assert

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat

/**
 */
class RaiffeisenAccountInfoScrapper implements AccountInfoScraper {

    private static final log = LogFactory.getLog(RaiffeisenAccountInfoScrapper.class)

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

            /* Server is sensitive on http's header Accept-Language settings -
               without this header set server defaults to english jvm locale and outputted html
               has strange formatting of dates.

               Our parsing (scraping) code expects html as it looks like when opened in browser
               (as browser sends http's Accept-Language header properly) */
            def client = new DefaultHttpClient()
            def getMethod = new HttpGet(url)
            getMethod.addHeader("Accept-Language", "en")
            def contentStream = client.execute(getMethod).getEntity().getContent()

            document = Jsoup.parse(contentStream, "UTF-8", "http://www.rb.cz")
        } else if (html) {
            document = Jsoup.parse(html)
        } else {
            throw new RuntimeException("No source of html to be scraped.")
        }

        account.number = (document.select("div#page div#text2 div#prava-cast h2")[0] =~ RE_ACCOUNT_NUMBER_SELECT)[0][1] + "/${BANK_CODE}"
        account.balance = new BigDecimal((document.select("div#page div#text2 div#prava-cast p strong")[1] =~ /(\d*.\d*) CZK<\/strong>/)[0][1])

        def rows = document.select("div#page div#text2 div#prava-cast table tbody tr")
        account.transactionItems = []
        account.totalSpend = 0
        account.totalIncome = 0
        rows.each {row ->
            def item = parseRow(row)
            if (item != null) {
                item.account = account
                account.transactionItems << item
                if (item.amount > 0) {
                    account.totalIncome += item.amount
                    account.countOfIncomingTransactions += 1
                } else {
                    account.totalSpend += item.amount
                }
            }
        }
        log.debug "Scraped ${account.transactionItems.size()} items."

        return account
    }

    /**
     *
     * Html snippet of row looks like this:<br/><br/>

     * <code>
     *   &lt;tr&gt;<br/>            &lt;td&gt;2012-10-21 22:50:16.0&lt;br /&gt;2012-10-21 22:50:16.0&lt;br /&gt;&lt;/td&gt;<br/>            &lt;td class=&quot;whitelc&quot;&gt; &lt;br /&gt;Jan Fischer 2013&lt;/td&gt;<br/>            &lt;td class=&quot;whitel&quot;&gt;2012-10-21 22:50:16.0&lt;br /&gt;2012-10-21 22:50:16.0&lt;br /&gt; P&#x0159;evod&lt;/td&gt;<br/>            &lt;td class=&quot;whitel&quot;&gt; &lt;br /&gt; &lt;br /&gt; &lt;/td&gt;<br/>            &lt;td class=&quot;whitep&quot;&gt;1559200.00&lt;br /&gt;&lt;/td&gt;<br/>            &lt;td class=&quot;whtransaction&quot;&gt;-5.00&lt;br /&gt;&lt;br /&gt;&lt;/td&gt;<br/>         &lt;/tr&gt;
     * </code>
     * <br/>
     *
     * <b>Note:</b> When there is no "Accept-Language: cs" header in http request dates are formatted
     * like in snippet above (ie. in american date format)
     *
     * @param e
     * @return
     */
    def TransactionItem parseRow(Element e) {
        TransactionItem item = new TransactionItem(amount: 0)

        def columns = e.select("td")

        def amountElement = columns[4]
        def amountText = amountElement.text()

        assert amountText != 0, "HTML element which should contain amount should not be empty."

        try {
            item.amount = new BigDecimal(getAmountFormat().parse(amountText))
        }
        catch (NumberFormatException nfe) {
            log.error("Cannot parse amount from text ${amountText}", nfe)
            throw new RuntimeException("Cannot parse amount from text ${amountText}", nfe)
        }

        item.description = columns[1].text()
        item.date = new SimpleDateFormat("dd.MM.yyyy hh:mm", Consts.CZECH).parse(columns[0].text())

        return item
    }

    private NumberFormat getAmountFormat() {
        def amountFormat = new DecimalFormat()
        def symbols = new DecimalFormatSymbols()
        symbols.setDecimalSeparator('.' as char)
        symbols.setGroupingSeparator(',' as char)
        amountFormat.setDecimalFormatSymbols(symbols)

        return amountFormat
    }

}
