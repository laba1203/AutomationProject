package util.logging;

import abstractObjects.AbstractElement;
import abstractObjects.AbstractElementsContainer;
import abstractObjects.AbstractTalkableFrame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import talkable.userRegistration.chosePlatformPage.ChosePlatformPage;

public class Log {
//    for containers:
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

    public static String fileIsNotProcessedMsg(){
        String msg = "File is not processed";
        System.out.println(msg);
        return msg;
    }

    public static String moveMouseOverMsg(AbstractElement element){
        String msg = "Mouse moved over the element: " +  element.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String frameSwitchedMsg(AbstractTalkableFrame frame){
        String msg = "Switched to iFrame " + frame.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String frameSwitchedMsg(){
        String msg = "Frame switched";
        System.out.println(msg);
        return msg;
    }

    public static String platformTypeSelectedMsg(ChosePlatformPage.PlatformType platformType){
        String msg = "The following platform is selected: " + platformType.toString();
        System.out.println(msg);
        return msg;
    }

    public static String userRegisteredMsg(String userName){
        String msg = "The following user is registered: " + userName;
        System.out.println(msg);
        return msg;
    }

    public static String userAndSiteCreatedMsg(String userName, String siteName){
        String msg = "The following user successfully registered: " + userName + "\r\nThe following site created for the above user: " + siteName + "\r\n*****";
        System.out.println(msg);
        return msg;
    }

    public static String elementClearedMsg(AbstractElement element){
        String msg = "Text is cleared in element: " + element.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String switchedToWindowMsg(String windowHandle){
        String msg = "Switched to Window: " + windowHandle;
        System.out.println(msg);
        return msg;
    }

    public static String rowIsNotFound(String campaignName){
        String msg = "Row is not found for " + campaignName;
        System.out.println(msg);
        return msg;
    }


}
