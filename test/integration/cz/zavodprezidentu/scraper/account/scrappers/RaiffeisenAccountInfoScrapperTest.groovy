package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import static grails.test.MockUtils.mockDomain

/**
 */
class RaiffeisenAccountInfoScrapperTest extends GroovyTestCase {
    private static final double DELTA = 0.0001

    /**
     * <b>Warning:</b> This test fails from within an IDE!
     */
    void testGetAccount() {
        mockDomain(Account)
        def scrapper = new RaiffeisenAccountInfoScrapper(
                url: "http://www.rb.cz/firemni-finance/transparentni-ucty/?tr_acc=vypis&account_number=22200011")

        def account = scrapper.getAccount()
        assertEquals("22200011/5550", account.getNumber())
    }

    void testParseRow() {
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

    void testParseFeeRow() {
        def scraper = new RaiffeisenAccountInfoScrapper();
        Document d = Jsoup.parse(""""\
            <table>
                <tr>
	                <td>01.11.2012<br>11:00<br></td>
	                <td class="whitelc">Vystavení potvrzení<br></td>
	                <td class="whitel">01.11.2012<br>01.11.2012<br>
	                Jiný trans. poplatek</td><td class="whitel"> <br>
	                898 <br>
	                </td><td class="whtransaction"><br></td>
	                <td class="whtransaction">-100,00<br><br></td>
                </tr>
            </table>
            """)
        assertNull(scraper.parseRow(d.select("tr")[0]))
    }
}
