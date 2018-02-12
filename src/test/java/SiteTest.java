import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import customerSite.talkableFrame.floatingWidgete.advocateSharePage.AdvocateSharePage;
import customerSite.talkableFrame.floatingWidgete.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.IntegrationInstructionPage.IntegrationInstructionPage;
import talkable.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.campaign.pages.campaignRulesPage.CampaignRulesPage;
import talkable.campaign.pages.createNewPurchasePage.CreateNewPurchasePage;
import talkable.homePage.HomePage;
import talkable.loginPage.LoginPage;
import talkable.reports.advocateOffersReport.advocateOfferDetailsPage.AdvocateOfferDetailsPage;
import talkable.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.userRegistration.chosePlatformPage.ChosePlatformPage;
import talkable.userRegistration.createAccountPage.CreateAccountPage;
import util.DriverConfig;
import util.logging.Log;

import java.util.ArrayList;
import java.util.Date;

import static talkable.userRegistration.chosePlatformPage.ChosePlatformPage.PlatformType.OTHER;

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
        CampaignRulesPage rulesPage = new CampaignRulesPage();
        rulesPage.createNewAdvocateIncentive();
        ArrayList<WebElement> elements = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".Rules-incentives-dropdown a>div:nth-of-type(1)"));

        System.out.println(elements.size());

        for (WebElement element: elements) {
            System.out.println("Text <" + element.getText() + ">");
        }

    }

    @Test
    public void t(){
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.submitLoginForm("", "");

    }



}
