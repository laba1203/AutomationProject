import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.userRegistration.chosePlatformPage.ChosePlatformPage;
import talkable.userRegistration.createAccountPage.CreateAccountPage;
import util.DriverConfig;

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
        ChosePlatformPage platformPage = new ChosePlatformPage();
        platformPage.selectPlatform(OTHER);
        CreateAccountPage createAccountPage = new CreateAccountPage();
        createAccountPage.clickCreateAccountButton();
        System.out.println("Email: " + createAccountPage.getElmntEmailInputErrorMsg());
        System.out.println("Password: " + createAccountPage.getPasswordInputErrorMsg());
        System.out.println("ConfirmPassword: " + createAccountPage.getConfirmPasswordInputErrorMsg());
        System.out.println("URL: " + createAccountPage.getSiteUrlInputErrorMsg());
        System.out.println("SiteName: " + createAccountPage.getSiteNameInputErrorMsg());
        System.out.println("\n\r**************\n\r");

        createAccountPage.populateForm("test@t.com", "Password@1", "Password@1", "autoSite01", "https://test.com", "SGD");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createAccountPage.clickCreateAccountButton();
    }



}
