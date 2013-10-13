package cz.zavodprezidentu.utils

import org.junit.Assert
import org.junit.Test

/**
 * @author Michal Bernhard (michal@bernhard.cz) 2013
 */
class StringUtilsTest {

    @Test def void "isBlank umi reagovat na non-break space"() {
       Assert.assertTrue(StringUtils.isBlank("   \u00A0  "))
    }

    @Test def void "isBlank umi reagovat na normalni ascii mezeru"() {
        Assert.assertTrue(StringUtils.isBlank("      "))
    }
}
