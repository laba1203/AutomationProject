import customerSite.talkableFrame.floatingWidgete.advocateSignupPage.AdvocateSignupPage;
import customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.loginPage.LoginPage;
import util.DriverConfig;

public class SiteTest {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = new DriverConfig().getDriver();
        driver.navigate().to("http://learn.talkable.com/QA-Max/Prod/home.html");
    }

    @Test
    public void test1(){
        AdvocateTriggerWidgetFrame advocateTriggerWidgetFrame = new AdvocateTriggerWidgetFrame();
        advocateTriggerWidgetFrame.click();
    }

    @Test
    public void test2_submitForm(){
        AdvocateSignupPage advocateSignupPage = new AdvocateSignupPage();
        advocateSignupPage.submitForm("Test_Name", "email@t.com");
    }

    @Test
    public void test3(){
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm("test", "test");

    }
}
