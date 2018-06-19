package testDataCreation;

import customerSite.talkableFrame.commonPages.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidget.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import util.DriverConfig;

public class AbTestImpressions_Standalone {

    private WebDriver driver;
    private static final String URL_TO_FW = "https://void-vika0518.myshopify.com/pages/share";

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

        driver.quit();
        DriverConfig.cleanWebDriver();
    }

    @Test
    public void repeat(){

        for (int i = 0; i<300; i++) {
            test1_makeImpression();
            System.out.println("\n\rCounter: " + i);
        }
    }



//    @AfterMethod
//    public void close(){
//        driver.quit();
//        new DriverConfig().cleanWebDriver();
//    }



}
