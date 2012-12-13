package cz.zavodprezidentu.domain

class Candidate {
    String name
    Account account = new Account(number: "", balance: 0)
    String image
    String accountUrl
    String wikiUrl

    static constraints = {
        name()
        account(nullable: true)
        image()
        accountUrl(nullable: true)
        wikiUrl(nullable: true)
    }
}
