package util;

import org.testng.Assert;

//**Class create to collect different technical methods
// *//

public class Util {

    public static String cutLastPartOfString(String initialString, String lastText){
        int endIndex = initialString.toCharArray().length - lastText.toCharArray().length;

        String out = initialString.substring(0, endIndex);
        if(!initialString.substring(endIndex).equals(lastText)){
            Assert.fail("FAILED: Sub-Text <" + lastText + "> is not present in the text <" + initialString + ">");
        }
        return out;
    }

    public static String cutFirstPartOfString(String initialString, String textToBeCut){
        int beginIndex = textToBeCut.toCharArray().length;

        String out = initialString.substring(beginIndex);
        if(!initialString.substring(0, beginIndex).equals(textToBeCut)){
            Assert.fail("FAILED: Sub-Text <" + textToBeCut + "> is not present in the text <" + initialString + ">");
        }
        return out;
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

    public static String getLastUrlResource(String initialUrl){
        String url = removeParametersFromUrl(initialUrl);
        char[] chars = url.toCharArray();
        int size = chars.length;
        int iterator = 0;
        for(int i = size-1; i > 0; i--){
            if(String.valueOf(chars[i]).equals("/")){
                iterator = i+1;
                System.out.println("LOG: Resource has been retrieved from URL. \r\nInitial URL: " + initialUrl);
                return url.substring(iterator);
            }
        }
        Assert.fail("FAILED: Input string is not an URL: <" + initialUrl + ">");
        return null;
    }

    public static String removeParametersFromUrl(String url){
        char[] chars = url.toCharArray();
        int size = chars.length;
        for(int i = chars.length-1; i > 0; i--){
            if(String.valueOf(chars[i]).equals("?")){
                return url.substring(0, i);
            }
        }
        System.out.println("LOG: URL doesn't contains any parameters. URL: <" + url + ">");
        return url;
    }

}
