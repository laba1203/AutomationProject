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
import talkable.userRegistration.chosePlatformPage.ChosePlatformPage;
import talkable.userRegistration.createAccountPage.CreateAccountPage;
import util.DriverConfig;
import util.logging.Log;

import java.util.Date;

import static talkable.userRegistration.chosePlatformPage.ChosePlatformPage.PlatformType.OTHER;

public class SiteTest {

    WebDriver driver;
    String shareLink;

    @BeforeClass
    public void setup(){
        driver = new DriverConfig().getDriver();
        driver.navigate().to("http://learn.talkable.com/QA-Max/void/email-test/home.html");
    }

    @Test
    public void test1(){
        shareLink = ClientSiteScenarios.completeAdvocateOfferForFloatingWidget("testName", "test@test.com");

    }

    @Test
    public void test2(){
        driver = ClientSiteScenarios.setupDriverWithCleanCookies(driver);
        driver.navigate().to(shareLink);
    }



}
