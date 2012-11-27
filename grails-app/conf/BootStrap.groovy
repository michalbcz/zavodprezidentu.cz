import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.Candidate
import cz.zavodprezidentu.scraper.account.scrappers.CeskaSporitelnaTransparentAccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.FioAccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.RaiffeisenAccountInfoScrapper
import grails.util.Environment

class BootStrap {

    def scraperService

    def init = { servletContext ->

         scraperService.runScrapers()

    }

    def destroy = {
    }

}
