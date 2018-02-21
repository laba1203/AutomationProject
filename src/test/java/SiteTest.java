import common.cases.CommonScenarios;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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
        driver.navigate().to("https://admin.void.talkable.com/sites/test1-617/campaigns/45360/edit#/incentives");
    }

    @Test
    public void test2(){
        PageCampaignRules rulesPage = new PageCampaignRules();
        rulesPage.createNewIncentive(AdvocateSignupIncentive, 5, Percentage, MultiUse);
        ArrayList<WebElement> elements = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".Rules-incentives-dropdown a>div:nth-of-type(1)"));

        System.out.println(elements.size());

        for (WebElement element: elements) {
            System.out.println("Text <" + element.getText() + ">");
        }
    }




}
