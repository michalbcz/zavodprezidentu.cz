package cz.zavodprezidentu.scraper.account.scrappers

import org.junit.Test

/**
 */
class RoithovaAccountInfoScraperOnlineIntegrationTest {

    @Test void testScraping() {
        def account = new RoithovaAccountInfoScraper(url: "http://www.roithova.cz/vypis_z_uctu/").getAccount()

    }
}
