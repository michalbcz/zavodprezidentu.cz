package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Test

import static org.junit.Assert.*

import static grails.test.MockUtils.mockDomain

/**
 */
class FioAccountInfoScraperTest {
    private static final double DELTA = 0.0001

    @Test void "account number is scraped right"() {
        def scrapper = new FioAccountInfoScraper(
                url: "https://www.fio.cz/scgi-bin/hermes/dz-transparent.cgi?ID_ucet=2600311696")

        def account = scrapper.getAccount()
        assertEquals("2600311696/2010", account.getNumber())
    }

    @Test void testParseRow() {
        def scraper = new FioAccountInfoScraper()
        Document d = Jsoup.parse("""\
            <table>
            <tr class="odd_row">
                <td>23.10.2012</td>
                <td class="tr">2 000,00</td>
                <td class="wrap">Bezhotovostn&iacute; př&iacute;jem</td>
                <td class="tr">0000</td>
                <td class="tr">&nbsp;</td>
                <td class="tr">&nbsp;</td>
                <td class="wrap">Jan Posp&iacute;šil</td>
                <td class="wrap">Velk&yacute; respekt za odhodl&aacute;n&iacute; a odvahu. Dr&sect;ˇm V&nbsp;m i Va&ccedil;emu skvŘl‚mu těmupalce.</td>
            </tr>
            </table>
            """)
        TransactionItem item = scraper.parseRow(d.select("tr")[0])
        assertEquals(new BigDecimal("2000"), item.getAmount(), DELTA)


    }
}
