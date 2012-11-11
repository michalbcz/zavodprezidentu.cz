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

    static constraints = {
    }
}
