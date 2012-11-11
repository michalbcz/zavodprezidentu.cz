package cz.zavodprezidentu.domain

class Account {
    String number;
    String bank;
    String url;
    BigDecimal balance;

    static belongsTo = [candidate:Candidate]
    static hasMany = [items:TransactionItem]

    static constraints = {
    }
}
