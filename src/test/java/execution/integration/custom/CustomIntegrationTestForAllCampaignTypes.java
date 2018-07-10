package execution.integration.custom;

import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;

import static talkable.common.CampaignPlacement.FloatingWidget;
import static talkable.common.CampaignPlacement.PostPurchase;
import static talkable.common.CampaignPlacement.Standalone;
import static talkable.common.CampaignType.AdvocateDashboard;
import static talkable.common.CampaignType.Invite;


/*Link to test scenario: https://docs.google.com/spreadsheets/d/1FLkr-T2s-mVnG770gLh4imwMnoO0vFtduYquM_49zzQ/edit#gid=0
* */
public class CustomIntegrationTestForAllCampaignTypes extends BaseTest{

    private static final String customerSiteUrl = PropertyLoader.loadEnvProperty("test.sites.simpleTestForAllCampaigns");
    private static final String siteName = PropertyLoader.loadProperty("sites.name.simpleTestForAllCampaigns");

    @BeforeClass
    public void setup() {
        this.driver.navigate().to(EnvFactory.getEnvUrl());
    }

    @Test
    public void login(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(EnvFactory.getCommonUser(), EnvFactory.getPassword());
    }

    @Test(dependsOnMethods = "login")
    public void createInviteFloatingWidget(){
        createCampaign(Invite, FloatingWidget);
    }

    @Test(dependsOnMethods = "login")
    public void createInviteStandalone(){
        createCampaign(Invite, Standalone);
    }

    @Test(dependsOnMethods = "login")
    public void createAdvocateDashboardFloatingWidget(){
        createCampaign(AdvocateDashboard, FloatingWidget);
    }

    @Test(dependsOnMethods = "login")
    public void createAdvocateDashboardStandalone(){
        createCampaign(AdvocateDashboard, Standalone);
    }

    @Test(dependsOnMethods = "login")
    public void createInvitePostPurchase(){
        createCampaign(Invite, PostPurchase);
    }

    @Test(dependsOnMethods = "login")
    public void createAdvocateDashboardPostPurchase(){
        createCampaign(AdvocateDashboard, PostPurchase);
    }


//    @Test(dataProvider = "getTestData", dependsOnMethods = "login", threadPoolSize = 1)
    private void createCampaign(CampaignType campaignType, CampaignPlacement campaignPlacement){
        String campaignName = "AUTO_TEST_" + TestDataGenerator.getRandomId();

        //open Talkable admin site:
        this.driver.navigate().to(EnvFactory.getAdminUrl());
        CommonScenarios.switchToIntegratedSiteByVisibleText(siteName);

        CommonScenarios.openCampaignsPage();
        CommonScenarios.deactivateAllCampaigns();

        //Create new campaign
        CommonScenarios.createNewCampaignFromCampaignsPage(campaignType, campaignPlacement);
        CommonScenarios.openCampaignRulesPage();
        CommonScenarios.setCampaignNameOnRulesPage(campaignName);
        CommonScenarios.launchCampaign();

        //Verification on Customer Site:
        if (ClientSiteScenarios.isCampaignOnCustomerSite(campaignType, campaignPlacement, getCustomerSiteUrl(campaignPlacement))){
            Log.testPassed(campaignPlacement.toString() + " is present on the site\r\n");
        }
        else{
            Assert.fail("FAILED: " + campaignType.toString() + ": " + campaignPlacement.toString() + " is not displayed on the customer site: " + getCustomerSiteUrl(campaignPlacement));
        }

        //re-open Talkable admin site:
        this.driver.navigate().to(EnvFactory.getAdminUrl());
        CommonScenarios.switchToIntegratedSiteByVisibleText(siteName);
        CommonScenarios.deactivateCampaign(campaignName);

        //Verify that campaign is inactive on the Customer Site:
        if (!ClientSiteScenarios.isCampaignOnCustomerSite(campaignType, campaignPlacement, getCustomerSiteUrl(campaignPlacement))){
            Log.testPassed(campaignPlacement.toString() + " is not displayed on the site\r\n");
        }
        else{
            Assert.fail("FAILED: " + campaignType.toString() + ": " + campaignPlacement.toString() + " is displayed on the customer site after deactivation");
        }
    }

//    @DataProvider
//    private Object[][] getTestData(){
//        return new Object[][]{
//                {Invite, FloatingWidget},
//                {Invite, CampaignPlacement.Standalone},
//                {CampaignType.AdvocateDashboard, FloatingWidget},
//                {CampaignType.AdvocateDashboard, CampaignPlacement.Standalone},
//                {Invite, PostPurchase},
//                {CampaignType.AdvocateDashboard, PostPurchase}
////                {CampaignType.RewardGleam, CampaignPlacement.Gleam}
//                //TODO: separate test should be added for Gleam
//        };
//    }

    private String getCustomerSiteUrl(CampaignPlacement placement){
        if(placement == PostPurchase){
            return customerSiteUrl + "pp.html";
        }
        return customerSiteUrl;
    }

}
