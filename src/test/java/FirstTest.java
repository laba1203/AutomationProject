
import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import util.DriverConfig;


public class FirstTest {

    private WebDriver driver;

    private String siteName = "automationSite";

    @BeforeSuite
    public void setup(){
        driver = DriverConfig.getDriver();
        driver.navigate().to("https://talkable.com");
    }

    @Test
    public void test1_login(){
        CommonScenarios.login("maxim.laba@talkable.com", "Password@1");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.navigate().to("https://admin.talkable.com/sites/testmax-shard2/fraud");
    }

//    @Test
//    public void test2() {
//        new FraudSettingsPage().setHighProfile();
//    }


}
