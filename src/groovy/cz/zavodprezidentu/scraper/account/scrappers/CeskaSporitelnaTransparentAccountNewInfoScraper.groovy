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

/**
 * Scraping of new layout of transparent account pages.
 * Eg. http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28403923090800-d00018255
 *
 * @author Michal Bernhard (michal@bernhard.cz) 2013
 * @see CeskaSporitelnaTransparentAccountInfoScraper
 */
class CeskaSporitelnaTransparentAccountNewInfoScraper implements AccountInfoScraper {

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

        def accountBalanceElement = document.select(".transparentAccount .perex tr:eq(3) td:eq(1)")
        def accountBalanceText = accountBalanceElement.text()
        account.balance = parseAmount(accountBalanceText)

        def accoutNumberElement = document.select(".transparentAccount .perex tr:eq(0) td:eq(1)")
        def accountNumber = accoutNumberElement.text()
        account.number = accountNumber
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

        def rows = document.select("#transactionTable tr")
        //skip header
        rows.remove(0)
        def transactionItems =
            rows.grep { row -> StringUtils.isNotBlank(row.select("td")[0].html().replace("&nbsp;", "").trim()) }
                    .collect { row ->  parseRow(row) }

        log.info(
                "Parsing page at url : ${pageUrl} DONE! " +
                        "Number of scraped transaction items: ${transactionItems.size()}")

        def nextPageLinkElement = document.select(".pagelinks a:contains(Další)")
        if (!nextPageLinkElement.isEmpty()) {

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
        def rowElement = row.select("td")[4]
        String amountHtml = rowElement.html()
        String amountText = ""

        //handle <p> in <td>
        if (amountHtml.contains("<p>")) {
            amountText = rowElement.select("p").text()
        } else {
            amountText = rowElement.text()
        }

        try {
            if (cz.zavodprezidentu.utils.StringUtils.isBlank(amountText)) {
                item.amount = 0
            } else {
                item.amount = parseAmount(amountText)
            }
        } catch (Exception ex) {
            def errorText = "Unable to parse amount from html: '${amountHtml}' (text: ${amountText}) " +
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
