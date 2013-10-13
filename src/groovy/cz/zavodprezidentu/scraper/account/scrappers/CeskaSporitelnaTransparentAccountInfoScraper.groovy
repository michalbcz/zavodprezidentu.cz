package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.TransactionItem
import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import org.apache.commons.lang.StringUtils
import org.apache.commons.logging.LogFactory
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.util.Assert

import java.text.NumberFormat

/**
 * This is not real implementation of scraper it's just adapter which according to html content
 * resolves which implementation of scraper use - new one or old one ?
 *
 * @author Michal Bernhard (michal@bernhard.cz) 2013
 *
 */
class CeskaSporitelnaTransparentAccountInfoScraper implements AccountInfoScraper {

    private static final log = LogFactory.getLog(this)

    def String url
    def String htmlAsString

    /**
     *
     * @param url url of page of transparent account eg. http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28403923090800-d00018255
     *
     */
    @Override
    Account getAccount() {

        Document document = null
        if (url != null) {
            document = Jsoup.connect(url).timeout(20000 /* 20s */).get()
        } else if (htmlAsString != null) {
            document = Jsoup.parse(htmlAsString)
        } else {
            throw new RuntimeException(
                    "Either url or htmlAsString have to be set otherwise scraper " +
                    "doesn't know from what source should be scraping.")
        }

        log.info("Resolving if this is new or old style od Ceska Sporitelna transparent account")

        def scraper;
        if(document.select(".transparentAccount")) {
            log.info("Using new transparent account page layout style...")
            scraper = new CeskaSporitelnaTransparentAccountNewInfoScraper()
        } else {
            log.info("Using old transparent account page layout style...")
            scraper = new CeskaSporitelnaTransparentAccountOldInfoScraper()
        }

        if (url) {
            scraper.url = url
        } else {
            scraper.htmlAsString = htmlAsString
        }

        return scraper.getAccount();

    }

}
