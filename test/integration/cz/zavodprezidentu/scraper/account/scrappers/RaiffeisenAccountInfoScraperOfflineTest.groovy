package cz.zavodprezidentu.scraper.account.scrappers

import org.junit.Test
import static org.junit.Assert.*

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

    @Test void "incoming transactions"() {
        assertEquals(11, scraper.getAccount().incomingTransactions)
    }

}
