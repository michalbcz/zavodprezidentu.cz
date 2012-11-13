package cz.zavodprezidentu.utils

import java.text.NumberFormat

/**
 */
class Consts {
    public static final Locale CZECH = new Locale("cs", "CZ")
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance(CZECH);
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("6000000");
    public static final BigDecimal ONE_PERCENT = MAX_AMOUNT / (100);

    static {
        Consts.NUMBER_FORMAT.setMinimumFractionDigits(2);
        Consts.NUMBER_FORMAT.setMaximumFractionDigits(2);
    }

}
