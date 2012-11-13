package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*
/**
 * @author Michal Bernhard (michal@bernhard.cz) 2012
 */
class CeskaSporitelnaTransparentAccountInfoScraperTests {
    private static final double DELTA = 0.0001

    Account account
    CeskaSporitelnaTransparentAccountInfoScraper scraper = new CeskaSporitelnaTransparentAccountInfoScraper()

    @Before
    public void beforeEachTestMethod() {
        URL url = new URL("http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28403923090800-d00018255")
        def scraper = new CeskaSporitelnaTransparentAccountInfoScraper(url: url)
        account = scraper.getAccount()
    }

    @Test
    public void testAccountNumber() {
        assertEquals("2840392309/0800", account.number)
    }

    @Test
    public void testParseRow() {
        String row = """\
            <table>
                <tr>
                    <td style=" vertical-align: top; text-align: left; height: 109px;">
                        <p>12.11.2012&nbsp;</p>
                        <p>09:28:09</p>
                    </td>
                    <td style=" vertical-align: top; text-align: left; height: 108px;">PETRA
                    KUBINOVÁ, DAROVACÍ SMLOUVA-KAREL SCHWARZENBERG</td>
                    <td style=" vertical-align: top; text-align: left; height: 109px;">
                        <p>12.11.2012&nbsp;</p>
                        <p>12.11.2012&nbsp;</p>
                        <p>Úhrada</p>
                    </td>
                    <td style=" vertical-align: top; text-align: left; height: 108px;">&nbsp;</td>
                    <td style=" vertical-align: top; text-align: left; height: 108px;">5.500,00</td>
                    <td style=" vertical-align: top; text-align: left; height: 108px;">&nbsp;</td>

                </tr>
             </table>
            """
        Document d = Jsoup.parse(row)
        TransactionItem item = scraper.parseRow(d.select("tr"))
        assertEquals(new BigDecimal("5500"), item.amount, DELTA)
    }
}
