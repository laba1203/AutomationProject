package execution.registration;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.IntegrationInstructionPage.IntegrationInstructionPage;
import util.DriverConfig;
import util.EnvFactory;
import util.TestDataGenerator;

import static talkable.userRegistration.chosePlatformPage.ChosePlatformPage.PlatformType.OTHER;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1VtMlmQDO77F5gGKOBBEuBN9hVyXBsnp3jD5u-v3z5Zk/
 * */
public class CreateNewUserWithSite extends BaseTest{
//    private DriverConfig driverFactory;
//    private WebDriver driver;

    private static final String email = "maxim.laba+auto.test" + TestDataGenerator.getRandomId() + "@talkable.com";
    private static final String password = "Password1";
    private static final String siteName = "autoSite" + TestDataGenerator.getRandomId();
    private static final String siteUrl = "https://auto.test.site.com";


    @BeforeClass
    public void setup() {
        //commented after adding of Base Test
//        this.driverFactory = new DriverConfig();
//        this.driver = this.driverFactory.getDriver();
        this.driver.navigate().to(EnvFactory.getRegistrationURL());
    }

    //Select Custom platform
    //Populate user registration form
    //Submit form
    //Check that new site is created
    @Test
    public void test1_register(){
        IntegrationInstructionPage page = CommonScenarios.registerNewUserWithSite(email, password, siteName, siteUrl, OTHER);
        Assert.assertEquals(page.header.getSiteName(), siteName);
    }

    //commented after adding of Base Test
//    @AfterClass
//    public void quit() {
//        driver.quit();
//        driverFactory.cleanWebDriver();
//    }

}
