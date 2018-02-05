import common.cases.CommonScenarios;
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

    @BeforeClass
    public void setup(){
        driver = new DriverConfig().getDriver();
        driver.navigate().to("https://admin.void.talkable.com/register?object_or_array");
    }

    @Test
    public void test1(){
        System.out.println("maxim.laba+auto" + (System.currentTimeMillis() + "").substring(5) + "@talkable.com");

    }

//    @Test
//    public void test2(){
//        new CommonScenarios().login("maxim.laba+auto36074920@talkable.com", "Password@1");
//
//        driver.navigate().to("https://admin.void.talkable.com/sites/test074920/integration");
//
//
//    }

//    @Test
//    public void test3(){
//        IntegrationInstructionPage integrationInstructionPage = new IntegrationInstructionPage();
//        System.out.println(integrationInstructionPage.header.getSiteName());
//    }

    @Test
    public void test2(){
        ChosePlatformPage.PlatformType platformType = OTHER;
        String email = "maxim.laba+auto" + (System.currentTimeMillis() + "").substring(5) + "@talkable.com";
        String password = "Password@1";
        String siteName = "test" + (System.currentTimeMillis() + "").substring(7);
        String siteUrl = "www.test.com";

        new ChosePlatformPage().selectPlatform(platformType);
        new CreateAccountPage().populateAndSubmitForm(email, password, siteName, siteUrl);

        IntegrationInstructionPage integrationInstructionPage = new IntegrationInstructionPage();

        Assert.assertEquals(integrationInstructionPage.header.getSiteName(), siteName);
        Log.userAndSiteCreatedMsg(email, siteName);


    }






}
