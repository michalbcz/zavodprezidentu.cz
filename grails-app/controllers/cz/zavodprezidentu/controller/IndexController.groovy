package cz.zavodprezidentu.controller

import cz.zavodprezidentu.domain.Candidate

class IndexController {

    def index() {
        [candidates: Candidate.list()]
    }
}
