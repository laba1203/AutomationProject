package execution.simpleTest;

import common.cases.CommonScenarios;
import customerSite.talkableFrame.commonPages.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidget.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.CampaignType;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.talkableSite.campaign.pages.launchCampaignPage.LaunchCampaignPage;
import util.DriverConfig;
import util.EnvFactory;
import util.TestDataGenerator;


/*Link to test scenario: https://docs.google.com/spreadsheets/d/1FLkr-T2s-mVnG770gLh4imwMnoO0vFtduYquM_49zzQ/edit#gid=0
* */
public class SimpleTestForAllCampaignTypes {

    private DriverConfig driverFactory;
    private WebDriver driver;

    private static final String customerSiteUrl = "http://learn.talkable.com/QA-Common/Automation/void/simple-test/";
    private static final String siteName = "simple-test";
    private String campaignDetailsPageUrl;


    @BeforeClass
    public void setup() {
        this.driverFactory = new DriverConfig();
        this.driver = this.driverFactory.getDriver();
        this.driver.navigate().to(EnvFactory.getEnvUrl());
        //Login to env
        CommonScenarios.login(EnvFactory.getUser(), EnvFactory.getPassword()).selectByVisibleText(siteName);
        driver.getCurrentUrl();


    }


    @Test(dataProvider = "getTestData")
    public void test(CampaignType campaignType, CampaignPlacement campaignPlacement){
        String campaignName = "AUTO_TEST_" + TestDataGenerator.getRandomId();
        //Create new campaign
        CampaignDetailsPage detailsPage = CommonScenarios.initiateCampaignCreation(campaignType, campaignPlacement);
        PageCampaignRules rulesPage = detailsPage.campaignNavigationMenu.openRulesPage();
        //set campaign name and click Launch Campaign
        LaunchCampaignPage launchPage = rulesPage.setCampaignName(campaignName).campaignNavigationMenu.launchCampaign();
        //launch campaign
        launchPage.launchCampaign();

        //Verification on Customer Site:
        //TBD

        //re-open Talkable admin site:
        this.driver.navigate().to(EnvFactory.getAdminUrl());
        //deactivate campaign:
        CommonScenarios.deactivateCampaign(campaignName);

        //Verify that campaign is inactive on the Customer Site:
        //TBD

    }

    @AfterClass
    public void quit() {
        driver.quit();
        driverFactory.cleanWebDriver();
    }


    @DataProvider
    private Object[][] getTestData(){
        return new Object[][]{
                {CampaignType.Invite, CampaignPlacement.FloatingWidget},
                {CampaignType.Invite, CampaignPlacement.Standalone},
                {CampaignType.Invite, CampaignPlacement.PostPurchase},
                {CampaignType.AdvocateDashboard, CampaignPlacement.FloatingWidget},
                {CampaignType.AdvocateDashboard, CampaignPlacement.Standalone},
                {CampaignType.AdvocateDashboard, CampaignPlacement.PostPurchase},
                {CampaignType.RewardGleam, CampaignPlacement.Gleam}
        };
    }


}
