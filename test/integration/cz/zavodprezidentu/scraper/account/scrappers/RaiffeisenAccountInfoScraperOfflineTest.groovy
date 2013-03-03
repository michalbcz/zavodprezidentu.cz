package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.utils.Consts
import org.junit.Test

import java.text.SimpleDateFormat

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * @author Michal Bernhard (michal@bernhard.cz) 2012
 */
class RaiffeisenAccountInfoScraperOfflineTest extends AbstractRaiffeisenAccountInfoScrapperTest {

    @Override
    def getScraper() {
        def htmlAsText = getClass().getResourceAsStream("test_data/rb_transparent_account.html").getText("utf-8")
        return new RaiffeisenAccountInfoScrapper(html: htmlAsText)
    }

    @Test void "sum of expenses aka total spend"() {
       assertEquals(-230981 as double, scraper.getAccount().getTotalSpend() as double, DELTA)
    }

    @Test void "incoming transactions count"() {
        assertEquals(11, scraper.getAccount().countOfIncomingTransactions)
    }

    @Test void "transaction item description"() {
        def transactionItems = scraper.getAccount().transactionItems
        def expectedTransactionItems = transactionItems.grep { TransactionItem item -> item.description.contains("tisk plakátů") }
        assertTrue(expectedTransactionItems.size() == 1)
    }

    @Test void "transaction item date"() {
        def transactionItems = scraper.getAccount().transactionItems
        def filterClosure = { TransactionItem item -> item.description.contains("tisk plakátů") }
        def expectedTransactionItem = (transactionItems.grep(filterClosure) as List)[0]
        def expectedDateTime = new SimpleDateFormat("dd.MM.yyyy hh:mm", Consts.CZECH).parse("16.11.2012 11:59")
        assertTrue(expectedTransactionItem.date == expectedDateTime)
    }

}
