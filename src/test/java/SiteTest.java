import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import customerSite.talkableFrame.floatingWidgete.advocateSharePage.AdvocateSharePage;
import customerSite.talkableFrame.floatingWidgete.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.IntegrationInstructionPage.IntegrationInstructionPage;
import talkable.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.userRegistration.chosePlatformPage.ChosePlatformPage;
import talkable.userRegistration.createAccountPage.CreateAccountPage;
import util.DriverConfig;
import util.logging.Log;

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
        driver.navigate().to("https://admin.void.talkable.com/sites/email-test/campaigns/45400#/");
    }

    @Test
    public void test2(){
        CampaignDetailsPage detailsPage = new CampaignDetailsPage();
        CampaignDetailsPage secondCampaign = detailsPage.copyCampaign();
        System.out.println("Campaign Name: " + secondCampaign.getCampaignName());
        System.out.println("Campaign Type: " + secondCampaign.getCampaignType());
        secondCampaign.copyCampaign("Third_campaign");
    }

}
