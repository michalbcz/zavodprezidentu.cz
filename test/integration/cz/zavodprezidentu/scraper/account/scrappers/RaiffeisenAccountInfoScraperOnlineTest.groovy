package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.junit.BeforeClass
import org.junit.Test

import static grails.test.MockUtils.mockDomain

/**
 */
class RaiffeisenAccountInfoScraperOnlineTest extends AbstractRaiffeisenAccountInfoScrapperTest {

    @Override
    def getScraper() {
        new RaiffeisenAccountInfoScrapper(
                url: "http://www.rb.cz/firemni-finance/transparentni-ucty/?tr_acc=vypis&account_number=22200011")
    }
}
