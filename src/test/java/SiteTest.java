import common.cases.CommonScenarios;
import execution.smoke.SmokeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.campaign.pages.campaignRulesPage.PageCampaignRules;
import util.DriverConfig;

import java.util.ArrayList;

import static talkable.campaign.pages.campaignRulesPage.PageCampaignRules.CouponCodeType.MultiUse;
import static talkable.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType.Percentage;
import static talkable.campaign.pages.campaignRulesPage.PageCampaignRules.IncentiveType.AdvocateSignupIncentive;


public class SiteTest {

    WebDriver driver;
    String shareLink;
    CommonScenarios commonScenarios = new CommonScenarios();

    @BeforeClass
    public void setup(){
        driver = new DriverConfig().getDriver();
        driver.navigate().to("https://void.talkable.com/");
    }

    @Test
    public void test1_login(){
        commonScenarios.login("maxim.laba@talkable.com", "Password@1");
        driver.navigate().to("https://admin.void.talkable.com/sites/automation-smoke-test/campaigns/45475#/");
    }

    @Test
    public void test2(){
        CampaignDetailsPage detailsPage = new CampaignDetailsPage();
        PageCampaignRules rules = detailsPage.campaignNavigationMenu.openRulesPage();
        rules.setCampaignName("test");

//        new SmokeTest().test04_setCampaignNameOnRulesPage();

    }


}



