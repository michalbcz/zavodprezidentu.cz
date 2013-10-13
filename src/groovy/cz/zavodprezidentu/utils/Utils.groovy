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

    public static boolean issBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
