package cz.zavodprezidentu.domain

class Account {
    String number;
    String bank;
    BigDecimal balance = 0;
    BigDecimal totalIncome = 0;
    BigDecimal totalSpend = 0;
    Integer countOfIncomingTransactions = 0;

    static belongsTo = [candidate : Candidate]
    static hasMany = [transactionItems : TransactionItem]

    static constraints = {
        bank(nullable: true)
        number()
        balance()
        totalIncome()
        totalSpend()
        countOfIncomingTransactions()
    }

}
