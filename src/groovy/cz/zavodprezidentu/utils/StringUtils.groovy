package cz.zavodprezidentu.utils

/**
 * @author Michal Bernhard (michal@bernhard.cz) 2013
 */
final class StringUtils {

    private StringUtils() {} /* non instantiable */

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isSpaceChar(Character.codePointAt(str,i)) == false)) {
                return false;
            }
        }
        return true;
    }

}
