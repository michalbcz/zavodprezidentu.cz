package cz.zavodprezidentu.controller

class AdminApiController {

    def scraperService

    def runScraper() {
        scraperService.runScrapers()
    }
}
