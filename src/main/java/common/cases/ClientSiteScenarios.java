package common.cases;

import customerSite.talkableFrame.commonPages.advocateSharePage.AdvocateSharePage;
import customerSite.talkableFrame.commonPages.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidget.advocateSharePage.AdvocateSharePageFW;
import customerSite.talkableFrame.floatingWidget.advocateSignupPage.AdvocateSignupPageFW;
import customerSite.talkableFrame.floatingWidget.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import util.DriverConfig;
import util.logging.Log;

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
        AdvocateSignupPageFW advocateSignupPageFW = advocateTriggerWidget.click();
        AdvocateSharePageFW advocateSharePageFW = advocateSignupPageFW.submitForm(advocateName, advocateEmail);
        return advocateSharePageFW.getShareLink();
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

    /*Verification that campaign is present on customer site
    * 1. Open site link.
    * 2. Check campaign.
    */
    public static boolean assertCampaignOnCustomerSite(CampaignPlacement placement, String siteLink){
        new DriverConfig().getDriver().navigate().to(siteLink);
        switch (placement){
            case FloatingWidget:
                return isFloatingPresent(placement);
            case Standalone:
                return isStandalonePresent(placement);
            case PostPurchase:
                return isPostPurchasePresent(placement);
            case Gleam:
                return isGleamPresent(placement);
            default:
                Assert.fail("FAILED: Unknown placement type" + placement);
                return false;
        }
    }

    private static boolean isFloatingPresent(CampaignPlacement placement){
        try{
            AdvocateTriggerWidgetFrame frame = new AdvocateTriggerWidgetFrame();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    private static boolean isStandalonePresent(CampaignPlacement placement){
        try{
            AdvocateSignupPage frame = new AdvocateSignupPage();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    private static boolean isPostPurchasePresent(CampaignPlacement placement){
        try{
            AdvocateSharePage frame = new AdvocateSharePage();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    private static boolean isGleamPresent(CampaignPlacement placement){
        Assert.fail("Gleam verification is not yet implemented");
        return false;
    }
}
