package execution.registration;

import common.cases.CommonScenarios;
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
public class CreateNewUserWithSite {
    private DriverConfig driverFactory;
    private WebDriver driver;

    private String email = "test" + TestDataGenerator.getRandomId() + "@test.com";
    private String password = "Password1";
    private String siteName = "autoSite" + TestDataGenerator.getRandomId();
    private String siteUrl = "https://auto.test.site.com";


    @BeforeClass
    public void setup() {
        this.driverFactory = new DriverConfig();
        this.driver = this.driverFactory.getDriver();
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

    @AfterClass
    public void quit() {
        driver.quit();
        driverFactory.cleanWebDriver();
        System.out.println("After class executed");
    }

}
