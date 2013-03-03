package cz.zavodprezidentu.scraper.account.scrappers

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.scraper.account.AccountInfoScraper

/**
 * This scraper choose right scraper implementation according for given url.
 * So you do not need manually decide and run scraper for particular urls.
 *
 * @author Michal Bernhard (michal@bernhard.cz) 2013
 */
class ScraperAdapter implements AccountInfoScraper {

    def String url

    @Override
    Account getAccount() {

        switch (url) {
            case ~/.*fio\.cz.*/ :
                return new FioAccountInfoScraper(url: url).getAccount()
            case ~/.*csas\.cz.*/ :
                return new CeskaSporitelnaTransparentAccountInfoScraper(url: url).getAccount()
            case ~/.*rb\.cz.*/ :
                return new RaiffeisenAccountInfoScrapper(url: url).getAccount()
            case ~/.*roithova.cz.*/ :
                return new RoithovaAccountInfoScraper(url: url).getAccount()
            default:
                throw new RuntimeException("${url} not supported!")
        }

    }
}
