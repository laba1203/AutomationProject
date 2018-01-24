package util.logging;

import abstractObjects.AbstractElement;
import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Log {
//    for elements:
    private static final String clickMsg = "Click to ";
    private static final String enterValueMsg = "Enter value: '";
    private static final String screenshotMsg = "Screenshot is available by the following path: ";



    public static String clickMsg(Object obj){
        String msg = clickMsg + obj.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String enterValueMsg(String value, Object obj){
        String msg = enterValueMsg + value + "' to " + obj.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String enterValueAndClickEnterMsg(String value, Object obj){
        String msg = "Enter value: '" + value + "' to " + obj.getClass().getName()+ ". And press ENTER/RETURN";
        System.out.println(msg);
        return msg;
    }

    public static String pageNotOpenedMsg(Object obj){
        String msg = obj.getClass().getName() + " page is not opened";
        System.out.println(msg);
        return msg;
    }

    public static String getScreenshotMsg() {
        return screenshotMsg;
    }

    public static String selectFromDropDownLogMsg(String selectedValue, Select dropdown){
        String msg = "Element " + selectedValue + " is selected in dropdown " + dropdown;
        System.out.println(msg);
        return msg;
    }

    public static String webElementIsNotActiveMsg(AbstractElement webElement){
        String msg = "WebElement is not active by the following locator" + webElement.getLocator();
        System.out.println(msg);
        return msg;
    }

    public static String incorrectCountOFWebElementsMsg(){
        String msg = "Incorrect count of WebElements by the locator";
        System.out.println(msg);
        return msg;

    }

    public static String popupIsNotOpenedMsg(AbstractElementsContainer popup){
        String msg = popup.getClass().getName() + "is not opened";
        System.out.println(msg);
        return msg;
    }

    public static String fileUploadedMsg(String fileName, AbstractElement target){
        String msg = "File: '" +fileName+ "' uploaded to element " + target.getClass().getName();
        System.out.println(msg);
        return msg;
    }


}
