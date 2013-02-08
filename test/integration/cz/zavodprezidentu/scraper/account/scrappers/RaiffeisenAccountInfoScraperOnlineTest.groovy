package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.utils.Consts
import org.apache.commons.lang.time.DateUtils
import org.hamcrest.CoreMatchers
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertThat
import static org.junit.Assert.assertTrue

/**
 */
class RaiffeisenAccountInfoScraperOnlineTest extends AbstractRaiffeisenAccountInfoScrapperTest {

    private static final String TRANSACTION_ITEM_FOR_TELEFONNI_POPLATKY_VODAFONE_DESCRIPTION = "telefonní poplatky Vodafone"

    @Override
    def getScraper() {
        new RaiffeisenAccountInfoScrapper(
                url: "http://www.rb.cz/firemni-finance/transparentni-ucty/?tr_acc=vypis&account_number=22200011")
    }

    @Test void testGetAccountAgainstWeb() {
        def account = scraper.getAccount()
        assertEquals("22200011/5550", account.getNumber())
    }

    @Test void "test parsing of transaction item description"() {

        def account = scraper.getAccount()
        def poplatkyVodafone = account.transactionItems.grep { TransactionItem item ->
            item.description.equals(TRANSACTION_ITEM_FOR_TELEFONNI_POPLATKY_VODAFONE_DESCRIPTION)
        }

        assertTrue(
                "We expect that transaction item with description " +
                "'${TRANSACTION_ITEM_FOR_TELEFONNI_POPLATKY_VODAFONE_DESCRIPTION}' exists.",
                poplatkyVodafone.size() >= 1)

    }

    @Test void "test parsing of transaction item date"() {

        def account = scraper.getAccount()

        def poplatkyVodafone = account.transactionItems.grep { TransactionItem item ->
            item.description.equals(TRANSACTION_ITEM_FOR_TELEFONNI_POPLATKY_VODAFONE_DESCRIPTION)
        }

        def expectedDate = Consts.DATE_FORMAT.parse("29.01.2013")
        def vodafonePoplatekForJanuary = poplatkyVodafone.grep { TransactionItem item ->
            DateUtils.truncatedEquals(item.date, expectedDate, Calendar.DAY_OF_MONTH)
        }

        assertThat(
                "Only one record should be returned for date: ${expectedDate} and" +
                "description: ${TRANSACTION_ITEM_FOR_TELEFONNI_POPLATKY_VODAFONE_DESCRIPTION}",
                vodafonePoplatekForJanuary.size(), CoreMatchers.is(1))

    }

    @Test void "test parsing of transaction item amount"() {

        def account = scraper.getAccount()

        def poplatkyVodafone = account.transactionItems.grep { TransactionItem item ->
            item.description.equals(TRANSACTION_ITEM_FOR_TELEFONNI_POPLATKY_VODAFONE_DESCRIPTION)
        }

        def withDateOfThe29thOfJanuary2013 = { TransactionItem item ->
            DateUtils.truncatedEquals(item.date, Consts.DATE_FORMAT.parse("29.01.2013"), Calendar.DAY_OF_MONTH)
        }
        def vodafonePoplatekForJanuary = poplatkyVodafone.grep(withDateOfThe29thOfJanuary2013).first()

        def expectingAmount = -32994.00
        assertTrue(
                "We are expecting amount ${expectingAmount} but is ${vodafonePoplatekForJanuary.amount}",
                expectingAmount == vodafonePoplatekForJanuary.amount)

    }


}
