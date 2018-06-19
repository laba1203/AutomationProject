import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import talkable.talkableSite.siteSettings.basic.SiteSettingsBasicTab;
import util.TestDataGenerator;
import util.Util;
import util.logging.Log;

public class Testing extends BaseTest {

    @Test
    public void login(){




        String siteName = "sitename";
        String siteID =  "siteid" + TestDataGenerator.getRandomId();
        String siteURL = "http://qewqwe.com";
        String platform = "Custom";
        String currency = "AUD";


        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login("hutornoy@talkable.com", "organ_Telo23" );
        CommonScenarios.openSiteSettingsPage();
        CommonScenarios.updateSiteSettingsBasicTab(siteName, siteID, siteURL, platform, currency);
        CommonScenarios.assertSiteSettigsBasicTab(siteName, siteID, siteURL);

        Log.testPassed("Site setting updated");
    }


        }
