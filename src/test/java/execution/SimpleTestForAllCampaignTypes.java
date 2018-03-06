package execution;

import common.cases.CommonScenarios;
import customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.CampaignType;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.talkableSite.campaign.pages.launchCampaignPage.LaunchCampaignPage;
import util.DriverConfig;
import util.EnvFactory;
import util.TestDataGenerator;

public class SimpleTestForAllCampaignTypes {

    private DriverConfig driverFactory;
    private WebDriver driver;

    private static final String customerSiteUrl = "";


    @BeforeClass
    public void setup() {
        this.driverFactory = new DriverConfig();
        this.driver = this.driverFactory.getDriver();
        this.driver.navigate().to(EnvFactory.getEnvUrl());
        //Login to env
        CommonScenarios.login(EnvFactory.getUser(), EnvFactory.getPassword());
    }


    @Test(dataProvider = "getTestData")
    public void test(CampaignType campaignType, CampaignPlacement campaignPlacement){
        String campaignName = "AUTO_TEST_" + TestDataGenerator.getRandomId();
        CampaignDetailsPage detailsPage = CommonScenarios.initiateCampaignCreation(campaignType, campaignPlacement);
        PageCampaignRules rulesPage = detailsPage.campaignNavigationMenu.openRulesPage();
        LaunchCampaignPage launchPage = rulesPage.setCampaignName(campaignName).campaignNavigationMenu.launchCampaign();
        launchPage.launchCampaign();

//        Verification on Customer Site:
//        this.driverFactory.getDriver().navigate().to(customerSiteUrl);
//        AdvocateTriggerWidgetFrame triggerWidget = new AdvocateTriggerWidgetFrame();

//        return to Talkable
        this.driver.navigate().to(EnvFactory.getAdminUrl());
//        deactivate campaign
        CommonScenarios.deactivateCampaign(campaignName);

        //Verify that campaign is inactive on the Customer Site:
//        try {
//            this.driverFactory.getDriver().navigate().to("http://learn.talkable.com/QA-Max/void/automation-smoke-test/index.html");
//            new AdvocateTriggerWidgetFrame();
//            Assert.fail("FAILED: Floating Widget is displayed. Test Failed");
//        } catch (NoSuchElementException e) {
//            System.out.println("PASSED: Floating Widget is not displayed");
//        }

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
