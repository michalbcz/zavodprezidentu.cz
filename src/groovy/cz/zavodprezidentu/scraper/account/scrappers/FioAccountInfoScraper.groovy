package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import java.text.NumberFormat

/**
 */
class FioAccountInfoScraper implements AccountInfoScraper {
    private static final String BANK_NAME = "Fio Bank"
    private String url;


    @Override
    Account getAccount() {
        def account = new Account()
        account.bank = BANK_NAME;

        Document document = Jsoup.connect(url.toString()).get()

        Element e = document.select(
                "span.main_header_row_text")[1]
        //"table.summary td.last"
        account.number = (e =~ /(\d+\/\d+)/)[0][0]
        account.balance = toBigDecimal(document.select("table.summary td.last")[0].html())

        def rows = document.select("table.main tbody tr")
        //remove last (total) row
        rows.remove(rows.size() - 1)

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
        log.debug "Scraped ${account.items.size()} items."
        return account
    }

    def TransactionItem parseRow(Element e) {
        TransactionItem item = new TransactionItem()
        try {
            item.with {
                amount = toBigDecimal(e.select("td")[1].html())
            }
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
