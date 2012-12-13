package cz.zavodprezidentu.utils

import java.text.DateFormat
import java.text.NumberFormat

/**
 */
class Consts {
    public static final Locale CZECH = new Locale("cs", "CZ")
    public static final NumberFormat NUMBER_FORMAT_CURRENCY = NumberFormat.getCurrencyInstance(CZECH);
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(CZECH);

    public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT, CZECH);

    static {
        Consts.NUMBER_FORMAT_CURRENCY.setMinimumFractionDigits(2);
        Consts.NUMBER_FORMAT_CURRENCY.setMaximumFractionDigits(2);
        Consts.NUMBER_FORMAT.setMinimumFractionDigits(0);
        Consts.NUMBER_FORMAT.setMaximumFractionDigits(0);
    }

}
