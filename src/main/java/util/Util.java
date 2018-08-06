package util;

import org.testng.Assert;
import util.logging.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        int iterator;
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

    public static String stringArrayToString(String[] arr){
        StringBuilder out = new StringBuilder();
        for (String str :
                arr) {
            out.append(str).append("\r\n");
        }
        return out.toString();
    }

    public static String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(new Date());
    }

    public static String generateItegratedPageCode(String tkblIntegrationScript){
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <title>Automation Site</title>\n" +
                "    <meta content='initial-scale=1, maximum-scale=1' name='viewport' />" +
//                "    <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0\">\n" +
//                "    <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw==\" crossorigin=\"anonymous\">\n" +
//                "    <link href=\"http://getbootstrap.com/examples/justified-nav/justified-nav.css\" rel=\"stylesheet\">\n" +
//                "    <script type=\"text/javascript\" src=\"//code.jquery.com/jquery-1.10.2.min.js\"></script>\n" +
//                "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha256-KXn5puMvxCw+dAYznun+drMdG1IFl3agK0p/pqT9KAo= sha512-2e8qq0ETcfWRI4HJBzQiA3UoyFk6tbNyG+qSaIBZLyW9Xf3sWZHN/lxe9fTh1U45DpPf07yj94KsUHHWe4Yk1A==\" crossorigin=\"anonymous\"></script>\n" +
                "\n" +
                tkblIntegrationScript + "\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"talkable-offer\"></div>\n" +
                "    <pre>\n" +
                "      Automation Site generated on "+getCurrentDate()+"\n" +
                "    </pre>\n" +
                "  </body>\n" +
                "</html>"
                ;
        Log.logRecord("Generated HTML page with Talkable script:\r\n" + html);
        return html;
    }

}
