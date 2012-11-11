package cz.zavodprezidentu.utils

/**
 */
class Utils {
    public static int getPercentage(BigDecimal amount) {
        return (amount / Consts.ONE_PERCENT) as Integer;
    }
}
