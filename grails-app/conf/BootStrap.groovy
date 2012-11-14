import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.Candidate
import cz.zavodprezidentu.scraper.account.scrappers.CeskaSporitelnaTransparentAccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.FioAccountInfoScraper
import cz.zavodprezidentu.scraper.account.scrappers.RaiffeisenAccountInfoScrapper
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

            new Candidate(
                    name: "Jana Bobošíková",
                    image: "jana_bobosikova.jpg",
                    accountUrl: null,
                    account: new Account(number: "1", balance: new BigDecimal("0"))).save(failOnError: true)

            new Candidate(
                    name: "Vladimír Dlouhý",
                    image: "vladimir_dlouhy.jpg",
                    account: new Account(number: "2", balance: new BigDecimal("0"))).save(failOnError: true)

            new Candidate(
                    name: "Taťána Fischerová",
                    image: "tatana_fischerova.jpg",
                    account: new Account(number: "3", balance: new BigDecimal("0"))).save(failOnError: true)

            new Candidate(
                    name: "Zuzana Roithová",
                    image: "zuzana_roithova.jpg",
                    account: new Account(number: "4", balance: new BigDecimal("0"))).save(failOnError: true)


            def zeman = new Candidate(
                    name: "Miloš Zeman",
                    image: "milos_zeman.jpg",
                    accountUrl: "http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-23902000730800-d00018326"
            )
            zeman.account = new CeskaSporitelnaTransparentAccountInfoScraper(url: zeman.accountUrl).account
            zeman.save()

            def sobotka = new Candidate(
                    name: "Přemysl Sobotka",
                    image:  "premysl_sobotka.jpg",
                    accountUrl: "http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28411593490800-d00018501"
            )
            sobotka.account = new CeskaSporitelnaTransparentAccountInfoScraper(url: sobotka.accountUrl).account
            sobotka.save()


            def schwarzenberg = new Candidate(
                    name: "Karel Schwarzenberg",
                    image: "karel_schwarzenberg.jpg",
                    accountUrl: "http://www.csas.cz/banka/nav/o-nas/transparentni-ucet-28403923090800-d00018255"
            )
            schwarzenberg.account = new CeskaSporitelnaTransparentAccountInfoScraper(url: schwarzenberg.accountUrl).account
            schwarzenberg.save()

            def dienstbier = new Candidate(
                    name: "Jiří Dienstbier",
                    image: "jiri_dienstbier.jpg",
                    accountUrl: "https://www.fio.cz/scgi-bin/hermes/dz-transparent.cgi?ID_ucet=2100280379"
            )
            dienstbier.account = new FioAccountInfoScraper(url: dienstbier.accountUrl).account
            dienstbier.save(failOnError: true)

            def franz = new Candidate(
                    name: "Vladimír Franz",
                    image: "vladimir_franz.jpg",
                    accountUrl: "https://www.fio.cz/scgi-bin/hermes/dz-transparent.cgi?ID_ucet=2600311696"
            )
            franz.account = new FioAccountInfoScraper(url: franz.accountUrl).account
            franz.save(failOnError: true)

            def tomio = new Candidate(
                    name: "Tomio Okamura",
                    image: "tomio_okamura.jpg",
                    accountUrl: "http://www.rb.cz/firemni-finance/transparentni-ucty/?tr_acc=vypis&account_number=7287984001"
            )
            tomio.account = new RaiffeisenAccountInfoScrapper(url: tomio.accountUrl).account
            tomio.save(failOnError: true)

            def fischer = new Candidate(
                    name: "Jan Fischer",
                    image: "jan_fischer.jpg",
                    accountUrl: "http://www.rb.cz/firemni-finance/transparentni-ucty/?tr_acc=vypis&account_number=22200011"
            )
            fischer.account = new RaiffeisenAccountInfoScrapper(url: fischer.accountUrl).account
            fischer.save(failOnError: true)

    }

    def destroy = {
    }

}
