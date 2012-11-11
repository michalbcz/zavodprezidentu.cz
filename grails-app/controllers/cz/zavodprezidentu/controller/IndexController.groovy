package cz.zavodprezidentu.controller

import cz.zavodprezidentu.domain.Account
import cz.zavodprezidentu.domain.Candidate

class IndexController {

    def index() {
        [accounts: Account.listOrderByBalance().reverse()]
    }
}
