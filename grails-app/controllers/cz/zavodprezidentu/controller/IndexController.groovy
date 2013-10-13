package cz.zavodprezidentu.controller

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.Scraper
import cz.zavodprezidentu.utils.Consts

class IndexController {

    def index() {
        forward(action: "income")
    }

    def about() {
        render(view: "about")
    }

    def balance() {
        def accounts = Account.listOrderByBalance()
        accounts = accounts.sort(byCandidateHasTransparentAccountComparator())
        accounts = accounts.reverse()
        def lastRun = Scraper.findAll().get(0).lastRun

        render(view: "index", model: [
                accounts: accounts,
                key: "balance",
                title: "Zůstatky na účtu",
                max: accounts[0].balance * 1.1,
                format: Consts.NUMBER_FORMAT_CURRENCY,
                lastRun: lastRun,
                description: "Aktuální zůstatky na účtu podle výpisu z banky"
        ])
    }

    def income() {
        def accounts = Account.listOrderByTotalIncome()
        accounts = accounts.sort(byCandidateHasTransparentAccountComparator())
        accounts = accounts.reverse()
        def lastRun = Scraper.findAll().get(0).lastRun

        render(view: "index", model: [
            accounts: accounts,
                key : "totalIncome",
                title: "Celkové příjmy",
                max: accounts[0].totalIncome * 1.1,
                format: Consts.NUMBER_FORMAT_CURRENCY,
                lastRun: lastRun,
                description: "Součty všech příchozích plateb na účet"
        ])
    }

    def expense() {
        def accounts = Account.listOrderByTotalSpend()
        accounts = accounts.sort(reverse(byCandidateHasTransparentAccountComparator()))
        def lastRun = Scraper.findAll().get(0).lastRun

        render(view: "index", model: [
                accounts: accounts,
                key : "totalSpend",
                title: "Celkové výdaje",
                max: accounts[0].totalSpend * 1.1,
                format: Consts.NUMBER_FORMAT_CURRENCY,
                lastRun: lastRun,
                description: "Součty všech odchozích plateb z účtu"
        ])
    }

    def transactions() {
        def accounts = Account.listOrderByCountOfIncomingTransactions().reverse()
        accounts = accounts.sort(reverse(byCandidateHasTransparentAccountComparator()))
        def lastRun = Scraper.findAll().get(0).lastRun

        render(view: "index", model: [
                accounts: accounts,
                key : "countOfIncomingTransactions",
                title: "Počet příspěvků",
                max: accounts[0].countOfIncomingTransactions * 1.1,
                format: Consts.NUMBER_FORMAT,
                lastRun: lastRun,
                description: "Počet příchozích transakcí na účet"
        ])
    }

    def private reverse(Closure closure) {
        { a, b -> -1 * closure.call(a, b) }
    }

    def private byCandidateHasTransparentAccountComparator() {
        return { Account account1, Account account2 ->

            if (account1.candidate.accountUrl) {
                if (account2.candidate.accountUrl) {
                    return 0
                } else {
                    return 1
                }
            } else {
                if (account2.candidate.accountUrl) {
                    return -1
                } else {
                    return 0
                }
            }

        }
    }

}
