package cz.zavodprezidentu.utils

import cz.zavodprezidentu.scraper.account.AccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.CeskaSporitelnaTransparentAccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.FioAccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.RaiffeisenAccountInfoScrapper

/**
 * Collections of utilities for scrapers.
 *
 * @see FioAccountInfoScraper
 * @see CeskaSporitelnaTransparentAccountInfoScraper
 * @see RaiffeisenAccountInfoScrapper
 *
 * @author Michal Bernhard (michal@bernhard.cz) 2012
 */
class Scrapers {

    /**
     *
     * @param url
     * @return suitable scraper implementation based on url to be scraped
     */
    def static AccountInfoScraper get(String url) {
       switch (url) {

           case ~/.*rb.cz.*/ : return new RaiffeisenAccountInfoScrapper(url: url);
           case ~/.*csas.cz.*/ : return new CeskaSporitelnaTransparentAccountInfoScraper(url: url);
           case ~/.*fio.cz.*/ : return new FioAccountInfoScraper(url: url);

           default: throw new RuntimeException("There is no suitable scraper for given url ($url).")
       }
    }

}
