package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import java.text.NumberFormat

class CeskaSporitelnaTransparentAccountInfoScraper implements AccountInfoScraper {

    def private String url


    /**
     *
     * @param url url of page of transparent account eg. http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28403923090800-d00018255
     *
     */

    @Override
    Account getAccount() {
        Document document = Jsoup.connect(url.toString()).get()
        def Account account = new Account()

        def accountBalanceText = document.select(".document-content strong").last().text()
        account.balance = parseAmount(accountBalanceText)
        account.number = (document.select("div.title").html() =~ /(\d+\/\d+)/)[0][0]
        account.items = []
        account.totalSpend = 0
        account.totalIncome = 0

        def rows = document.select("table.redheading tr")
        //skip header
        rows.remove(0)

        rows.each {row ->
            def item = parseRow(row)
            if (item != null) {
                item.account = account
                account.items << item
                if (item.amount > 0) {
                    account.totalIncome += item.amount
                    account.incomingTransactions += 1
                } else {
                    account.totalSpend += item.amount
                }
            }
        }
        log.debug "Scraped ${account.items.size()} items."
        return account

    }

    private Number parseAmount(String amountAsText) {

        def amountAsTextNormalized = amountAsText - "CZK" // remove currency
        amountAsTextNormalized = amountAsTextNormalized.replaceAll(/\./, "") // normalize to czech format of number

        def formatter = NumberFormat.getInstance(new Locale("cs"))
        def amount = formatter.parse(amountAsTextNormalized)

        return amount
    }

    /**
     * @param row
     * @return
     */
    def TransactionItem parseRow(Element row) {
        TransactionItem item = new TransactionItem()
        String s = row.select("td")[4].html()
        //handle <p> in <td>
        if (s.contains("<p>")) {
            s = row.select("td")[4].select("p").html()
        }
        item.amount = parseAmount(s.replace(" ", "").replace("&nbsp;", "").trim())

        return item
    }
}
