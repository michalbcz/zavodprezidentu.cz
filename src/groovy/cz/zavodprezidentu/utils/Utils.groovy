package cz.zavodprezidentu.utils

/**
 */
class Utils {
    public static int getPercentage(BigDecimal amount, BigDecimal max) {
        def onePercent = max / (100)
        return (amount / onePercent) as Integer;
    }
}
