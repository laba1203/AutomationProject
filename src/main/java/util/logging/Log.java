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
    private static final String clickMsg = "LOG: Click to ";
    private static final String enterValueMsg = "LOG: Enter value: '";
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
        String msg = "LOG: Enter value: '" + value + "' to " + obj.getClass().getName()+ ". And press ENTER/RETURN";
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
        String msg = "LOG: Element " + selectedValue + " is selected in dropdown " + dropdown;
        System.out.println(msg);
        return msg;
    }

    public static String testPassed(String message){
        String msg = "PASSED: " + message;
        System.out.println(msg);
        return msg;
    }

    public static String webElementIsNotActiveMsg(AbstractElement webElement){
        String msg = "LOG: WebElement is not active by the following locator" + webElement.getLocator();
        System.out.println(msg);
        return msg;
    }

    public static String incorrectCountOFWebElementsMsg(){
        String msg = "LOG: Incorrect count of WebElements by the locator";
        System.out.println(msg);
        return msg;

    }

    public static String popupIsNotOpenedMsg(){
        String msg = "LOG: Popup is not opened";
        System.out.println(msg);
        return msg;
    }

    public static String fileUploadedMsg(String fileName, AbstractElement target){
        String msg = "LOG: File: '" +fileName+ "' uploaded to element " + target.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String fileIsNotProcessedMsg(){
        String msg = "LOG: File is not processed";
        System.out.println(msg);
        return msg;
    }

    public static String moveMouseOverMsg(AbstractElement element){
        String msg = "Mouse moved over the element: " +  element.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String frameSwitchedMsg(AbstractTalkableFrame frame){
        String msg = "LOG: Switched to iFrame " + frame.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String frameSwitchedMsg(){
        String msg = "LOG: Frame switched";
        System.out.println(msg);
        return msg;
    }

    public static String platformTypeSelectedMsg(ChosePlatformPage.PlatformType platformType){
        String msg = "LOG: The following platform is selected: " + platformType.toString();
        System.out.println(msg);
        return msg;
    }

    public static String userRegisteredMsg(String userName){
        String msg = "LOG: The following user is registered: " + userName;
        System.out.println(msg);
        return msg;
    }

    public static String userAndSiteCreatedMsg(String userName, String siteName){
        String msg = "LOG: The following user successfully registered: " + userName + "\r\nThe following site created for the above user: " + siteName + "\r\n*****";
        System.out.println(msg);
        return msg;
    }

    public static String elementClearedMsg(AbstractElement element){
        String msg = "LOG: Text is cleared in element: " + element.getClass().getName();
        System.out.println(msg);
        return msg;
    }

    public static String switchedToWindowMsg(String windowHandle){
        String msg = "LOG: Switched to Window: " + windowHandle;
        System.out.println(msg);
        return msg;
    }

    public static String rowIsNotFound(String campaignName){
        String msg = "LOG: Row is not found for " + campaignName;
        System.out.println(msg);
        return msg;
    }

    public static String changesAreSaved(){
        String msg = "LOG: Changes have been saved";
        System.out.println(msg);
        return msg;
    }

    public static String alertAccepted(){
        String msg = "LOG: Click OK in Alert";
        System.out.println(msg);
        return msg;
    }

    public static String campaignDeactivated(String name){
        String msg = "LOG: Campaign has been deactivated. Campaign Name: " + name;
        System.out.println(msg);
        return msg;
    }

    public static String campaignDeleted(String name){
        String msg = "LOG: Campaign deleted. Campaign Name: " + name;
        System.out.println(msg);
        return msg;
    }

    public static String tableIsMissed(String status){
        String msg = "LOG: Table with <"+ status +"> campaigns is not displayed";
        System.out.println(msg);
        return msg;
    }

}
