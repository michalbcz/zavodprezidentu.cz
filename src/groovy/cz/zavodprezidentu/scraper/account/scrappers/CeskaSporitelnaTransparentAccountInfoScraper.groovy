package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.text.NumberFormat

class CeskaSporitelnaTransparentAccountInfoScraper implements AccountInfoScraper {

    def private URL url


    /**
     *
     * @param url url of page of transparent account eg. http://www.scrappers.cz/banka/nav/o-nas/transparentni-ucet-23902000730800-d00018326
     *
     */
    CeskaSporitelnaTransparentAccountInfoScraper(URL url) {
        this.url = url
    }

    @Override
    Account getAccount() {

        Document document = Jsoup.connect(url.toString()).get()

        def accountBalanceText = document.select(".document-content strong").get(3).text()
        Number accountBalance = parseAmount(accountBalanceText)

        return new Account(balance: accountBalance as BigDecimal)

    }

    private Number parseAmount(String amountAsText) {

        def amountAsTextNormalized = amountAsText - "CZK" // remove currency
        amountAsTextNormalized = amountAsTextNormalized.replaceAll(/\./, "") // normalize to czech format of number

        def formatter = NumberFormat.getInstance(new Locale("cs"))
        def amount = formatter.parse(amountAsTextNormalized)

        return amount
    }
}
