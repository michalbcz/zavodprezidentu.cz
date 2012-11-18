package cz.zavodprezidentu.scraper.account.scrappers

import org.junit.Test

/**
 * @author Michal Bernhard (michal@bernhard.cz) 2012
 */
class RaiffeisenAccountInfoScraperOfflineTest extends AbstractRaiffeisenAccountInfoScrapperTest {

    @Override
    def getScraper() {
        def htmlAsText = getClass().getResourceAsStream("test_data/rb_transparent_account.html").getText("utf-8")
        return new RaiffeisenAccountInfoScrapper(html: htmlAsText)
    }

}
