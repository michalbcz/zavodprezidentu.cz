package cz.zavodprezidentu.domain

class Account {
    String number;
    String bank;
    BigDecimal balance;
    BigDecimal totalIncome;
    BigDecimal totalSpend;

    static belongsTo = [candidate:Candidate]
    static hasMany = [items:TransactionItem]

    static constraints = {
        bank(nullable: true)
        number(unique: true)
        balance()
        totalIncome(nullable: true)
        totalSpend(nullable: true)
    }

}
