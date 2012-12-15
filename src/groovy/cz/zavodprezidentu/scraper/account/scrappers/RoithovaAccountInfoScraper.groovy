package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/**
 * @author Michal Bernhard (michal@bernhard.cz) 2012
 */
class RoithovaAccountInfoScraper implements AccountInfoScraper {

    def url

    @Override
    Account getAccount() {
        def account = new Account()
        account.bank = "žádná banka";

        Document document = Jsoup.connect(url.toString()).get()
        account.number = ""

        def rows = document.select("tr")
        rows.remove(0) // removing first row as it is used as a table header

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
                    account.incomingTransactions += 1
                } else {
                    account.totalSpend += item.amount
                }
            }
        }

        account.balance = account.totalIncome - Math.abs(account.totalSpend)

        log.debug "Scraped ${account.items.size()} items."
        return account
    }


    def TransactionItem parseRow(Element e) {
        TransactionItem item = new TransactionItem()
        try {

            def columns = e.select("td")
            def date = columns[0].text()
            def targetAccount = columns[1].text()
            def targetDescription = columns[2].text()
            def amount = toBigDecimal(columns[3].text())
            def typ = columns[4].text()

            if (typ.contains("Odchozí platba")) {
               amount = amount * -1 // outcome
            }

            item.amount = amount
        } catch (Exception exception) {
            log.error("Cannot parse row: ${e}", exception)
            throw exception
        }
        return item

    }

    private static final BigDecimal toBigDecimal(String s) {
        return new BigDecimal(s.replace(' CZK', '').replace(' ','').replace(',', '.'))
    }

}
