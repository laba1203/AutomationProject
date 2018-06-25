package execution.site.settings;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;

public class SiteSettingsBasicTab extends BaseTest {

    @BeforeClass
    public void login(){
        String email = PropertyLoader.loadProperty("talkable.user.siteSettings");
        String password = EnvFactory.getPassword();

        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(email, password );
    }

    @Test
    public void updateSiteSettingsBasicTab(){
        String siteName = "sitename";
        String siteID =  "siteid" + TestDataGenerator.getRandomId();
        String siteURL = "http://website.com";
        String platform = "Custom";
        String currency = "AUD";

        CommonScenarios.openSiteSettingsPage();
        CommonScenarios.updateSiteSettingsBasicTab(siteName, siteID, siteURL, platform, currency);
        CommonScenarios.assertErrorMsgSiteSettigsBasicTab(siteName, siteID, siteURL);

        Log.testPassed("Site setting updated");
    }
    @Test //site name blank
    public void siteSettingsBasicTabNameIsBlank(){
        String siteName = " ";
        String siteID =  "siteid" + TestDataGenerator.getRandomId();
        String siteURL = "http://website.com";
        String platform = "Custom";

        CommonScenarios.openSiteSettingsPage();
        CommonScenarios.populateSiteBasicNegativeTest(siteName, siteID, siteURL, platform);


        String ErrorMsg = "can't be blank, is too short (minimum is 3 characters)";
        CommonScenarios.assertErrorMsgSiteSettigsBasicTab(ErrorMsg);

        Log.testPassed("Test PAASED");
    }
    @Test //site ULR is invalid
    public void siteSettingsBasicTabInvalidURL(){
        String siteName = "sitename";
        String siteID =  "siteid" + TestDataGenerator.getRandomId();
        String siteURL = "http://website";
        String platform = "Custom";

        CommonScenarios.openSiteSettingsPage();
        CommonScenarios.populateSiteBasicNegativeTest(siteName, siteID, siteURL, platform);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String errorMsg = "is not valid";
        CommonScenarios.assertErrorMsgSiteSettigsBasicTab(errorMsg);

        Log.testPassed("Test PAASED");
    }
    @Test //site ID invalid
    public void siteSettingsBasicTabIDInvalid(){
        String siteName = "sitename";
        String siteID =  "siteID" + TestDataGenerator.getRandomId();
        String siteURL = "http://website.com";
        String platform = "Custom";

        CommonScenarios.openSiteSettingsPage();
        CommonScenarios.populateSiteBasicNegativeTest(siteName, siteID, siteURL, platform);


        String errorMsg = "should contain lowercase letters (a-z), digits (0-9) and dashes (-) only";
        CommonScenarios.assertErrorMsgSiteSettigsBasicTab(errorMsg);

        Log.testPassed("Test PAASED");
    }

    @Test //Not shopify URL
    public void siteSettingsBasicTabSiteNotShopify(){
        String siteName = "sitename";
        String siteID =  "siteid" + TestDataGenerator.getRandomId();
        String siteURL = "http://website.com";
        String platform = "Shopify";


        CommonScenarios.openSiteSettingsPage();
        CommonScenarios.populateSiteBasicNegativeTest(siteName, siteID, siteURL, platform);


        String errorMsg = "your site is not Shopify";
        CommonScenarios.assertErrorMsgSiteSettigsBasicTab(errorMsg);

        Log.testPassed("Test PAASED");
    }
}

