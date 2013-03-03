package cz.zavodprezidentu.domain

class TransactionItem {

    Date date;
    BigDecimal amount;
    String type;
    String ks;
    String vs;
    String ss;
    String identification;
    String description;

    static belongsTo = [account: Account]

    static constraints = {
        date(nullable: true)
        amount()
        type(nullable: true)
        ks(nullable: true)
        vs(nullable: true)
        ss(nullable: true)
        identification(nullable: true)
        description(nullable: true)
    }
}
