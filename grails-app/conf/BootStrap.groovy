import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.Candidate
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            println "Development environment"


            new Candidate(name: "Jana Bobošíková", image: "jana_bobosikova.jpg",  account:
                    new Account(number: "123/0100", balance: new BigDecimal("100500"), bank: "KB a.s.")).save(failOnError: true)
            new Candidate(name: "Jiří Dienstbier", image: "jiri_dienstbier.jpg", account:
                    new Account(number: "330855001/5500", balance: new BigDecimal("3000000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Vladimír Dlouhý", image: "vladimir_dlouhy.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("800000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Jan Fischer", image: "jan_fischer.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("1800000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Taťána Fischerová", image: "tatana_fischerova.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("80000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Vladimír Franz", image: "vladimir_franz.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("400000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Tomio Okamura", image: "tomio_okamura.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("200000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Zuzana Roithová", image: "zuzana_roithova.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("500000.34"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Karel Schwarzenberg", image: "karel_schwarzenberg.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("1200000.50"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Přemysl Sobotka", image:  "premysl_sobotka.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("20000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Miloš Zeman", image: "milos_zeman.jpg", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("900000.50"), bank: "Raifka")).save(failOnError: true)

        }
    }


    def destroy = {
    }
}
