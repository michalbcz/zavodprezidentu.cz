package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.TransactionItem
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.BeforeClass
import org.junit.Test

import static org.junit.Assert.*

/**
 */
public abstract class AbstractRaiffeisenAccountInfoScrapperTest {

    protected static final double DELTA = 0.0001

    def abstract getScraper();

    @Test void testGetAccountAgainstWeb() {
        def account = scraper.getAccount()
        assertEquals("22200011/5550", account.getNumber())
    }

    @Test void testParseRow() {
        def scraper = new RaiffeisenAccountInfoScrapper()
        Document d = Jsoup.parse("""\
            <table>
                <tr>
                    <td>2012-10-21 22:50:16.0<br />2012-10-21 22:50:16.0<br /></td>
                    <td class="whitelc"> <br />Jan Fischer 2013</td>
                    <td class="whitel">2012-10-21 22:50:16.0<br />2012-10-21 22:50:16.0<br /> Převod</td>
                    <td class="whitel"> <br /> <br /> </td>
                    <td class="whitep">1559200.00<br /></td>
                    <td class="whtransaction">-5.00<br /><br /></td>
                </tr>
            </table>
            """)
        TransactionItem item = scraper.parseRow(d.select("tr")[0])
        assertEquals(new Double("1559200.00"), item.amount, DELTA)
    }

}
