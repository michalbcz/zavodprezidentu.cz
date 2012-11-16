package cz.zavodprezidentu.utils

/**
 */
class Utils {
    public static int getPercentage(BigDecimal amount, BigDecimal max) {
        def onePercent = max / (100)
        def percentage = amount / onePercent
        if (percentage < 1 && percentage > 0) {
            percentage = 1
        }

        return percentage as Integer;
    }
}
