package cz.zavodprezidentu.utils

import java.text.NumberFormat

/**
 */
class Consts {
    public static final Locale CZECH = new Locale("cs", "CZ")
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance(CZECH);

    static {
        Consts.NUMBER_FORMAT.setMinimumFractionDigits(2);
        Consts.NUMBER_FORMAT.setMaximumFractionDigits(2);
    }

}
