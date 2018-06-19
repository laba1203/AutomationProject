
import common.cases.CommonScenarios;
import common.cases.functionalities.EditorScenarios;
import execution.BaseTest;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import util.DriverConfig;
import util.EnvFactory;
import util.Screenshot;
import util.TestDataGenerator;
import util.logging.Log;

import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.CouponCodeType.MultiUse;
import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType.Percentage;
import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.IncentiveType.AdvocateReferralIncentive;


public class FirstTest{

//    private WebDriver driver;

//    private String siteName = "automationSite";

    public WebDriver driver;

//    @BeforeSuite
    public void setup(){
        Log.logRecord("Class name: " + this.getClass().getName());
        this.driver = DriverConfig.getDriver();
        this.driver.navigate().to(EnvFactory.getEnvUrl());
        CommonScenarios.login("maxim.laba@talkable.com", "Password@1");
        driver.navigate().to("https://admin.void.talkable.com/sites/custom/campaigns/45135/editor#/view_setups/147514/preset_slug/default-preset");
    }

    @Test
    public void test1(){
//        new PageCampaignRules().createNewIncentive(
//                AdvocateReferralIncentive,
//                6,
//                Percentage,
//                MultiUse);

        PageCampaignRules rulesPage = new PageCampaignRules();
        rulesPage = rulesPage.createNewIncentive(
                PageCampaignRules.IncentiveType.AdvocateSignupIncentive,
                25, PageCampaignRules.DiscountType.FixedAmount,
                PageCampaignRules.CouponCodeType.MultiUse);

        rulesPage.createNewIncentive(PageCampaignRules.IncentiveType.FriendIncentive_NewCustomer,
                10, PageCampaignRules.DiscountType.Percentage,
                PageCampaignRules.CouponCodeType.MultiUse);

    }

//    @Test
//    public void test2() {
//        new FraudSettingsPage().setHighProfile();
//    }

    @Test
    public void accept(){
        this.driver = DriverConfig.getDriver();
        this.driver.navigate().to(EnvFactory.getEnvUrl());
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login("maxim.laba@talkable.com", "Password@1");
//        driver.navigate().to("https://admin.void.talkable.com/sites/custom/campaigns/45135/editor#/view_setups/147514/preset_slug/default-preset");
    }

}
