import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.Candidate
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            println "Development environment"

            new Candidate(name: "Vladimír Franz", account:
                    new Account(number: "123/0100", balance: new BigDecimal("100500"), bank: "KB a.s.")).save(failOnError: true)
            new Candidate(name: "Lukáš Marek", account:
                    new Account(number: "330855001/5500", balance: new BigDecimal("3000000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Michal Bernhard", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("800000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Michal Bernhard", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("1800000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Lukáš Marek", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("80000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Michal Bernhard", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("400000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Michal Bernhard", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("200000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Lukáš Marek", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("500000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Michal Bernhard", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("1200000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Lukáš Marek", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("20000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Michal Bernhard", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("900000"), bank: "Raifka")).save(failOnError: true)
            new Candidate(name: "Michal Bernhard", account:
                    new Account(number: "330855007/5500", balance: new BigDecimal("800000"), bank: "Raifka")).save(failOnError: true)

        }
    }


    def destroy = {
    }
}
