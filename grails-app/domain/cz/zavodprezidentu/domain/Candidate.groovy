package cz.zavodprezidentu.domain

class Candidate {
    String name;
    Account account;
    String image;
    String accountUrl;

    static constraints = {
        name()
        account(nullable: true)
        image()
        accountUrl(nullable: true)
    }
}
