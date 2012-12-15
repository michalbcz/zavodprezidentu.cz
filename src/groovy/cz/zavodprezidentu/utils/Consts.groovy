package cz.zavodprezidentu.utils

import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat

/**
 */
class Consts {
    public static final Locale CZECH = new Locale("cs", "CZ")
    public static final NumberFormat NUMBER_FORMAT_CURRENCY = NumberFormat.getCurrencyInstance(CZECH)
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(CZECH)

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy")

    static {
        Consts.NUMBER_FORMAT_CURRENCY.setMinimumFractionDigits(2)
        Consts.NUMBER_FORMAT_CURRENCY.setMaximumFractionDigits(2)
        Consts.NUMBER_FORMAT.setMinimumFractionDigits(0)
        Consts.NUMBER_FORMAT.setMaximumFractionDigits(0)
    }

}
