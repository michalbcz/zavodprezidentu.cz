package cz.zavodprezidentu.utils

import cz.zavodprezidentu.scraper.account.scrappers.CeskaSporitelnaTransparentAccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.FioAccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.RaiffeisenAccountInfoScrapper
import cz.zavodprezidentu.utils.Scrapers
import org.junit.Test

import static org.junit.Assert.*

/**
 * @author Michal Bernhard (michal@bernhard.cz) 2012
 */
class ScraperTest {

    @Test void "should return scraper for Raiffaisen bank"() {
        def url = "http://www.rb.cz/firemni-finance/transparentni-ucty/?tr_acc=vypis&account_number=22200011"
        def scraper = Scrapers.get(url)
        assertEquals(RaiffeisenAccountInfoScrapper.class, scraper.class)
    }

    @Test void "should return scraper for Ceska sporitelna"() {
        def url = "http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-23902000730800-d00018326"
        def scraper = Scrapers.get(url)
        assertEquals(CeskaSporitelnaTransparentAccountInfoScraper.class, scraper.class)
    }

    @Test void "should return scraper for Fio"() {
        def url = "https://www.fio.cz/scgi-bin/hermes/dz-transparent.cgi?pohyby_DAT_od=16.07.2012&ID_ucet=2100280379"
        def scraper = Scrapers.get(url)
        assertEquals(FioAccountInfoScraper.class, scraper.class)
    }

}
