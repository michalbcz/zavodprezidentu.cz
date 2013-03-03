package zavod.prezidentu

import cz.zavodprezidentu.domain.Candidate
import cz.zavodprezidentu.domain.Scraper
import cz.zavodprezidentu.scraper.account.scrappers.ScraperAdapter

class ScraperService {

    def runScrapers() {

        /* delete any accounts if exists */
        log.info("Deleting all candidates if exsists in db.")
        def allCandidates = Candidate.findAll()
        allCandidates*.delete(flush: true) /* need to flush ie delete immediately otherwise saving fails */

        def lastScraperRun = Scraper.findAll()
        lastScraperRun*.delete(flush: true)

        log.info("Deleted ${allCandidates.size()} candidates")

        log.info("Scraping and saving candidates...")

        def onlyCandidatesWithTransparentAccounts = { Candidate presidentialCandidate ->
                presidentialCandidate.accountUrl != null
        }

        presidentialCandidates()
                .grep(onlyCandidatesWithTransparentAccounts)
                .each { Candidate presidentialCandidate ->
                    def scrapedAccountData = new ScraperAdapter(url: presidentialCandidate.accountUrl).getAccount()

                    presidentialCandidate.account = scrapedAccountData
                    presidentialCandidate.save()
                }

        Scraper lastRun = new Scraper(lastRun: new Date())
        lastRun.save()

    }

    private Collection<Candidate> presidentialCandidates() {

        def allCandidates = []

        def fisherova = new Candidate(
                name: "Taťána Fischerová",
                image: "tatana_fischerova.jpg",
                wikiUrl:  "http://cs.wikipedia.org/wiki/T%C3%A1%C5%88a_Fischerov%C3%A1",
                accountUrl: "https://www.fio.cz/scgi-bin/hermes/dz-transparent.cgi?pohyby_DAT_od=17.1.2012&ID_ucet=2900301705")
        allCandidates.add(fisherova)

        def roithova = new Candidate(
                name: "Zuzana Roithová",
                image: "zuzana_roithova.jpg",
                wikiUrl: "http://cs.wikipedia.org/wiki/Zuzana_Roithov%C3%A1",
                accountUrl: "http://www.roithova.cz/vypis_z_uctu/")
        allCandidates.add(roithova)

        def zeman = new Candidate(
                name: "Miloš Zeman",
                image: "milos_zeman.jpg",
                wikiUrl: "http://cs.wikipedia.org/wiki/Milo%C5%A1_Zeman",
                accountUrl: "http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-23902000730800-d00018326"
        )
        allCandidates.add(zeman)

        def sobotka = new Candidate(
                name: "Přemysl Sobotka",
                image:  "premysl_sobotka.jpg",
                wikiUrl: "http://cs.wikipedia.org/wiki/P%C5%99emysl_Sobotka",
                accountUrl: "http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28411593490800-d00018501"
        )
        allCandidates.add(sobotka)

        def schwarzenberg = new Candidate(
                name: "Karel Schwarzenberg",
                image: "karel_schwarzenberg.jpg",
                wikiUrl: "http://cs.wikipedia.org/wiki/Karel_Schwarzenberg",
                accountUrl: "http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28403923090800-d00018255"
        )
        allCandidates.add(schwarzenberg)

        def dienstbier = new Candidate(
                name: "Jiří Dienstbier",
                image: "jiri_dienstbier.jpg",
                wikiUrl: "http://cs.wikipedia.org/wiki/Ji%C5%99%C3%AD_Dienstbier_mlad%C5%A1%C3%AD",
                accountUrl: "https://www.fio.cz/scgi-bin/hermes/dz-transparent.cgi?pohyby_DAT_od=16.07.2012&ID_ucet=2100280379"
        )
        allCandidates.add(dienstbier)

        def franz = new Candidate(
                name: "Vladimír Franz",
                image: "vladimir_franz.jpg",
                wikiUrl: "http://cs.wikipedia.org/wiki/Vladim%C3%ADr_Franz",
                accountUrl: "https://www.fio.cz/scgi-bin/hermes/dz-transparent.cgi?pohyby_DAT_od=02.10.2012&ID_ucet=2600311696"
        )
        allCandidates.add(franz)

        def fischer = new Candidate(
                name: "Jan Fischer",
                image: "jan_fischer.jpg",
                wikiUrl: "http://cs.wikipedia.org/wiki/Jan_Fischer",
                accountUrl: "http://www.rb.cz/firemni-finance/transparentni-ucty/?tr_acc=vypis&account_number=22200011"
        )
        allCandidates.add(fischer)

        def bobosikova = new Candidate(
                name: "Jana Bobošíková",
                image: "jana_bobosikova.jpg",
                accountUrl: null,
                wikiUrl: "http://cs.wikipedia.org/wiki/Jana_Bobo%C5%A1%C3%ADkov%C3%A1")
        allCandidates.add(bobosikova)

        return allCandidates

    }
}
