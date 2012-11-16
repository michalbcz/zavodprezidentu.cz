package cz.zavodprezidentu.domain

class Account {
    String number;
    String bank;
    BigDecimal balance = 0;
    BigDecimal totalIncome = 0;
    BigDecimal totalSpend = 0;

    static belongsTo = [candidate:Candidate]
    static hasMany = [items:TransactionItem]

    static constraints = {
        bank(nullable: true)
        number(unique: true)
        balance()
        totalIncome()
        totalSpend()
    }

}
