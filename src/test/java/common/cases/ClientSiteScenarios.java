package common.cases;

import customerSite.talkableFrame.floatingWidgete.advocateSharePage.AdvocateSharePage;
import customerSite.talkableFrame.floatingWidgete.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.WebDriver;
import util.DriverConfig;

public class ClientSiteScenarios {

    /*Scenario to create share link by Advocate
    Precondition: Page with Talkable FloatingWidgete should be opened
     * 1. Click FW
     * 2. Enter advocate values(@advocateName, @advocateEmail) and click Invite Friend
     * 3. Copy share link.
     * Returns: Share link.
      * */
    public static String completeAdvocateOfferForFloatingWidget(String advocateName, String advocateEmail){
        AdvocateTriggerWidgetFrame advocateTriggerWidget = new AdvocateTriggerWidgetFrame();
        AdvocateSignupPage advocateSignupPage = advocateTriggerWidget.click();
        AdvocateSharePage advocateSharePage = advocateSignupPage.submitForm(advocateName, advocateEmail);
        return advocateSharePage.getShareLink();
    }

    /*Technical method to setup new driver instace with clean cookies.
    * Please note: New driver instance will be applied for all further scenarios*/
    public static WebDriver setupDriverWithCleanCookies(WebDriver driver){
        DriverConfig driverConfig = new DriverConfig();
        driver.manage().deleteAllCookies();
        driver.quit();
        driverConfig.cleanWebDriver();

        return new DriverConfig().getDriver();
    }
}
