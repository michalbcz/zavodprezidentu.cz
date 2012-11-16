package cz.zavodprezidentu.controller

import cz.zavodprezidentu.domain.Account

class IndexController {

    def balance() {
        def accounts = Account.listOrderByBalance().reverse()
        render(view: "/index", model: [
                accounts: accounts,
                key: "balance",
                title: "Zůstatky na účtu",
                max: accounts[0].balance * 1.1
        ])
    }

    def income() {
        def accounts = Account.listOrderByTotalIncome().reverse()
        render(view: "/index", model: [
            accounts: accounts,
                key : "totalIncome",
                title: "Celkové příjmy",
                max: accounts[0].totalIncome * 1.1
        ])
    }

    def expense() {
        def accounts = Account.listOrderByTotalSpend()
        render(view: "/index", model: [
                accounts: accounts,
                key : "totalSpend",
                title: "Celkové výdaje",
                max: accounts[0].totalSpend * 1.1
        ])
    }

}
