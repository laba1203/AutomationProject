
import common.cases.CommonScenarios;
import execution.BaseTest;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import util.DriverConfig;
import util.EnvFactory;
import util.Screenshot;
import util.logging.Log;

import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.CouponCodeType.MultiUse;
import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType.Percentage;
import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.IncentiveType.AdvocateReferralIncentive;


public class FirstTest{

//    private WebDriver driver;

//    private String siteName = "automationSite";

    public WebDriver driver;

    @BeforeSuite
    public void setup(){
        Log.logRecord("Class name: " + this.getClass().getName());
        this.driver = DriverConfig.getDriver();
        this.driver.navigate().to(EnvFactory.getEnvUrl());
        CommonScenarios.login("maxim.laba@talkable.com", "Password@1");
        driver.navigate().to("https://admin.talkable.com/sites/custom/campaigns/60628/edit#/");
    }

    @Test
    public void test1(){
        new PageCampaignRules().createNewIncentive(
                AdvocateReferralIncentive,
                6,
                Percentage,
                MultiUse);

    }

//    @Test
//    public void test2() {
//        new FraudSettingsPage().setHighProfile();
//    }



}
