package testDataCreation;

import customerSite.talkableFrame.commonPages.advocateSignupPage.AdvocateSignupPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import util.DriverConfig;
import util.TestDataGenerator;

public class AbTestImpressions_Standalone {

    private WebDriver driver;
    private static final String URL_TO_FW = "https://testvikanew1412.myshopify.com/pages/share";

//    @BeforeMethod
//    public void setup(){
//
//        driver = new DriverConfig().getDriver();
//        driver.navigate().to("http://learn.talkable.com/QA-Max/void/email-test/home.html");
//    }

    @Test
    public void test1_makeImpression(){
        driver = DriverConfig.getDriver();
        driver.navigate().to(URL_TO_FW);
        new AdvocateSignupPage();

//        driver.quit();
        DriverConfig.quitAndRemoveWebDriver();
    }

    public void makeImpressionOnSharePage(){
        driver = DriverConfig.getDriver();
        driver.navigate().to(URL_TO_FW);

        new AdvocateSignupPage()
                .submitForm(
                        "user" + TestDataGenerator.getRandomId(),
                        "example+" + TestDataGenerator.getRandomId() + "@gmail.com"
                );
//        new AdvocateSharePageForInvite();

//        driver.quit();
        DriverConfig.quitAndRemoveWebDriver();
    }

    @Test
    public void repeat(){

        for (int i = 0; i<300; i++) {
            makeImpressionOnSharePage();
            System.out.println("\n\rCounter: " + i);
        }
    }



//    @AfterMethod
//    public void close(){
//        driver.quit();
//        new DriverConfig().quitAndRemoveWebDriver();
//    }



}
