package testDataCreation;

import customerSite.talkableFrame.floatingWidget.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import util.DriverConfig;

public class AbTestImpressions_FloatingWidget {

    private WebDriver driver;
    private static final String URL_TO_FW = "http://learn.talkable.com/QA-Max/bastion/site1001/";

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

        AdvocateTriggerWidgetFrame button = new AdvocateTriggerWidgetFrame();
//        button.click();

//        driver.quit();
        DriverConfig.quitAndRemoveWebDriver();
    }

    @Test
    public void repeat(){

        for (int i = 0; i<5000; i++) {
            test1_makeImpression();
            System.out.println("\n\rCounter: " + i);
        }
    }



//    @AfterMethod
//    public void close(){
//        driver.quit();
//        new DriverConfig().quitAndRemoveWebDriver();
//    }



}
