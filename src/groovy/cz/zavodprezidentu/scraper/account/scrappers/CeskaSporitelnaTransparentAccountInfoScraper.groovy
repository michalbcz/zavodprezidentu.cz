package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import org.apache.commons.lang.StringUtils
import org.apache.commons.logging.LogFactory
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.util.Assert

import java.text.NumberFormat

class CeskaSporitelnaTransparentAccountInfoScraper implements AccountInfoScraper {

    private static final log = LogFactory.getLog(this)

    def String url
    def String htmlAsString

    /**
     *
     * @param url url of page of transparent account eg. http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28403923090800-d00018255
     *
     */

    @Override
    Account getAccount() {

        Document document = null
        if (url != null) {
            document = Jsoup.connect(url).get()
        } else if (htmlAsString != null) {
            document = Jsoup.parse(htmlAsString)
        } else {
            throw new RuntimeException(
                    "Either url or htmlAsString have to be set otherwise scraper " +
                    "doesn't know from what source should be scraping.")
        }
        def Account account = new Account()

        def accountBalanceText = document.select(".document-content strong:containsOwn(CZK)").text()
        account.balance = parseAmount(accountBalanceText)
        account.number = (document.select("div.title").html() =~ /(\d+\/\d+)/)[0][0]
        account.transactionItems = []
        account.totalSpend = 0
        account.totalIncome = 0

        def transactionItems = crawlAndParsePagesStartingAt(url)

        transactionItems.each { transactionItem ->
            transactionItem.account = account
            account.transactionItems << transactionItem
            if (transactionItem.amount > 0) {
                account.totalIncome += transactionItem.amount
                account.countOfIncomingTransactions += 1
            } else {
                account.totalSpend += transactionItem.amount
            }
        }

        log.debug "Scraped ${account.transactionItems.size()} items."
        return account

    }

    private List<TransactionItem> crawlAndParsePagesStartingAt(String pageUrl) {

        log.info("Parsing page at url : $pageUrl")
        Document document = Jsoup.connect(pageUrl).get()

        def rows = document.select("table.redheading tr")
        //skip header
        rows.remove(0)
        def transactionItems =
                rows.grep { row -> StringUtils.isNotBlank(row.select("td")[0].html().replace("&nbsp;", "").trim()) }
                    .collect { row ->  parseRow(row) }

        log.info(
                "Parsing page at url : ${pageUrl} DONE! " +
                "Number of scraped transaction items: ${transactionItems.size()}")

        def nextPageLinkElement = document.select("a:contains(Přejít na následující)")
        if (!nextPageLinkElement.isEmpty()) {

            def numberOfNextPageLinks = nextPageLinkElement.size()
            Assert.isTrue(
                    numberOfNextPageLinks == 1,
                    "We assume that only one link to next page exist on the" +
                    "page. But we found ${numberOfNextPageLinks} links.")

            def nextPageUrl = nextPageLinkElement.first().absUrl("href")
            log.debug("Page contains link to next page (${nextPageUrl})")
            transactionItems += crawlAndParsePagesStartingAt(nextPageUrl)

        }

        return transactionItems

    }

    @SuppressWarnings("GrMethodMayBeStatic")
    private Number parseAmount(String amountAsText) {

        def amountTextWithoutWhitespaces = amountAsText.replace(" ", "").replace("&nbsp;", "").trim()
        def amountAsTextNormalized = amountTextWithoutWhitespaces - "CZK" // remove currency
        amountAsTextNormalized = amountAsTextNormalized.replaceAll(/\./, "") // normalize to czech format of number

        def formatter = NumberFormat.getInstance(new Locale("cs"))
        def amount = formatter.parse(amountAsTextNormalized)

        return amount
    }

    def TransactionItem parseRow(Element row) {
        TransactionItem item = new TransactionItem()
        String amountRawText = row.select("td")[4].html()
        //handle <p> in <td>
        if (amountRawText.contains("<p>")) {
            amountRawText = row.select("td")[4].select("p").html()
        }

        try {
            item.amount = parseAmount(amountRawText)
        } catch (Exception ex) {
            def errorText = "Unable to parse amount from text: '${amountRawText}' " +
                    "extracted from transaction row " +
                    "represented by this html: '${row.toString()}'" +
                    "from page ${row.baseUri()}. " +
                    "Previous sibling has this html: ${row.previousElementSibling().toString()}."

            log.error(errorText, ex)
            throw new RuntimeException(errorText, ex)
        }

        return item
    }

}
