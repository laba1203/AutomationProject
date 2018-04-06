package execution.simple.test;

import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.talkableSite.campaign.pages.launchCampaignPage.LaunchCampaignPage;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.headerFrame.Header;
import talkable.talkableSite.siteDashboardPage.SiteDashboardPage;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;

import static talkable.common.CampaignPlacement.PostPurchase;


/*Link to test scenario: https://docs.google.com/spreadsheets/d/1FLkr-T2s-mVnG770gLh4imwMnoO0vFtduYquM_49zzQ/edit#gid=0
* */
public class SimpleTestForAllCampaignTypes extends BaseTest{

    private static final String customerSiteUrl = PropertyLoader.loadEnvProperty("test.sites.simpleTestForAllCampaigns");
    private static final String siteName = PropertyLoader.loadProperty("sites.name.simpleTestForAllCampaigns");

    @BeforeClass
    public void setup() {
        this.driver.navigate().to(EnvFactory.getEnvUrl());
        //Login to env
        CommonScenarios.login(EnvFactory.getUser(), EnvFactory.getPassword());
    }

    @Test(dataProvider = "getTestData")
    public void test(CampaignType campaignType, CampaignPlacement campaignPlacement){
        String campaignName = "AUTO_TEST_" + TestDataGenerator.getRandomId();

        //re-open Talkable admin site:
        this.driver.navigate().to(EnvFactory.getAdminUrl());
        new Header().selectByVisibleText(siteName);
        SiteDashboardPage dashboardPage = new SiteDashboardPage().verifySiteName(siteName);
        //deactivate all live campaigns:
        PageCampaigns campaignsPage = dashboardPage.header.openCampaignsPage().deactivateAllLiveCampaigns();

        //Create new campaign
        CampaignDetailsPage detailsPage = campaignsPage.createNewCampaign(campaignType, campaignPlacement);
        PageCampaignRules rulesPage = detailsPage.campaignNavigationMenu.openRulesPage();
        //set campaign name and click Launch Campaign
        LaunchCampaignPage launchPage = rulesPage.setCampaignName(campaignName).campaignNavigationMenu.launchCampaign();
        //launch campaign
        launchPage.launchCampaign();

        //Verification on Customer Site:
        if (ClientSiteScenarios.assertCampaignOnCustomerSite(campaignType, campaignPlacement, getCustomerSiteUrl(campaignPlacement))){
            Log.testPassed(campaignPlacement.toString() + " is present on the site\r");
        }
        else{
            Assert.fail("FAILED: " + campaignType.toString() + ": " + campaignPlacement.toString() + " is not displayed on the customer site");
        }

        //re-open Talkable admin site:
        this.driver.navigate().to(EnvFactory.getAdminUrl());
        new Header().selectByVisibleText(siteName);
        //deactivate campaign:
        CommonScenarios.deactivateCampaign(campaignName);

        //Verify that campaign is inactive on the Customer Site:
        if (!ClientSiteScenarios.assertCampaignOnCustomerSite(campaignType, campaignPlacement, getCustomerSiteUrl(campaignPlacement))){
            Log.testPassed(campaignPlacement.toString() + " is not displayed on the site\r");
        }
        else{
            Assert.fail("FAILED: " + campaignType.toString() + ": " + campaignPlacement.toString() + " is displayed on the customer site after deactivation");
        }
    }

    @DataProvider
    private Object[][] getTestData(){
        return new Object[][]{
                {CampaignType.Invite, CampaignPlacement.FloatingWidget},
                {CampaignType.Invite, CampaignPlacement.Standalone},
                {CampaignType.AdvocateDashboard, CampaignPlacement.FloatingWidget},
                {CampaignType.AdvocateDashboard, CampaignPlacement.Standalone},
                {CampaignType.Invite, PostPurchase},
                {CampaignType.AdvocateDashboard, PostPurchase}//,
//                {CampaignType.RewardGleam, CampaignPlacement.Gleam}
                //TODO: separate test should be added for Gleam
        };
    }

    private String getCustomerSiteUrl(CampaignPlacement placement){
        if(placement == PostPurchase){
            return customerSiteUrl + "pp.html";
        }
        return customerSiteUrl;
    }

}