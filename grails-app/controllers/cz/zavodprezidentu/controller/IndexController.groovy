package cz.zavodprezidentu.controller

import cz.zavodprezidentu.domain.Account
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

        render(view: "index", model: [
                accounts: accounts,
                key: "balance",
                title: "Zůstatky na účtu",
                max: accounts[0].balance * 1.1,
                format: Consts.NUMBER_FORMAT_CURRENCY
        ])
    }

    def income() {
        def accounts = Account.listOrderByTotalIncome()
        accounts = accounts.sort(byCandidateHasTransparentAccountComparator())
        accounts = accounts.reverse()

        render(view: "index", model: [
            accounts: accounts,
                key : "totalIncome",
                title: "Celkové příjmy",
                max: accounts[0].totalIncome * 1.1,
                format: Consts.NUMBER_FORMAT_CURRENCY
        ])
    }

    def expense() {
        def accounts = Account.listOrderByTotalSpend()
        accounts = accounts.sort(reverse(byCandidateHasTransparentAccountComparator()))

        render(view: "index", model: [
                accounts: accounts,
                key : "totalSpend",
                title: "Celkové výdaje",
                max: accounts[0].totalSpend * 1.1,
                format: Consts.NUMBER_FORMAT_CURRENCY
        ])
    }

    def transactions() {
        def accounts = Account.listOrderByIncomingTransactions().reverse()
        accounts = accounts.sort(reverse(byCandidateHasTransparentAccountComparator()))

        render(view: "index", model: [
                accounts: accounts,
                key : "incomingTransactions",
                title: "Počet příspěvků",
                max: accounts[0].incomingTransactions * 1.1,
                format: Consts.NUMBER_FORMAT
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
