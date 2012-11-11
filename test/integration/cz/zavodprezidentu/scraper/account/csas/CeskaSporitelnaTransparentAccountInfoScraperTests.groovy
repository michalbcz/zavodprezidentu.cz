package cz.zavodprezidentu.scraper.account.csas

import cz.zavodprezidentu.domain.Account
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*
/**
 * @author Michal Bernhard (michal@bernhard.cz) 2012
 */
class CeskaSporitelnaTransparentAccountInfoScraperTests {

    Account account

    @Before
    public void beforeEachTestMethod() {
        URL url = new URL("http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-23902000730800-d00018326")
        def scraper = new CeskaSporitelnaTransparentAccountInfoScraper(url)
        account = scraper.getAccount()
    }

    @Test
    public void "scrape account balance"() {
        assertThat(account.balance, is(1049421.74))
    }

}
