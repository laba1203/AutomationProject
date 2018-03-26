package util;

import org.testng.Assert;

//**Class create to collect different technical methods
// *//

public class Util {

    public static String getDifference(String string, String endSubString) {
        int stringSize = string.toCharArray().length;
        int subStringSize = endSubString.toCharArray().length;

        if (!string.substring(subStringSize).equals(endSubString)) {
            Assert.fail("FAILED: Sub-Text <" + endSubString + "> is not present in the text <" + string + ">");

        }
        return string.substring(subStringSize);

    }

    public static String getDifference(String string, String startSubString, String endSubString) {
        int stringSize = string.toCharArray().length;
        int startStringSize = startSubString.toCharArray().length;
        int endStringSize = endSubString.toCharArray().length;

        if ((startStringSize + endStringSize) >= stringSize) {
            Assert.fail("FAILED: Size of Sub-text parts cannot be size of the string: \r\n - String: <" + string + "> \r\n - Start sub-string: <" + startSubString + ">\r\n - Start sub-string: <" + startSubString + ">");
        }
        if (!string.substring(0, startStringSize).equals(startSubString) && !string.substring(endStringSize).equals(endSubString)) {
            Assert.fail("Values are not equal in the sub-text parts and main text: \r\n - String: <" + string + "> \r\n - Start sub-string: <" + startSubString + ">\r\n - Start sub-string: <" + endSubString + ">");
        }

        return string.substring(startStringSize, stringSize - endStringSize);

    }

    public static String getLastUrlResource(String url){
        char[] chars = url.toCharArray();
        int size = chars.length;
        int iterator = 0;
        for(int i = size-1; i > 0; i--){
            if(String.valueOf(chars[i]).equals("/")){
                iterator = i+1;
                return url.substring(iterator);
            }
        }
        Assert.fail("FAILED: Input string is not an URL: <" + url + ">");
        return null;
    }

}
