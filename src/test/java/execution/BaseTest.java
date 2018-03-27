package execution;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import util.DriverConfig;
import util.EnvFactory;
import util.Screenshot;

public class BaseTest {
        public DriverConfig driverFactory;
        public WebDriver driver;
        public Screenshot screenshot = new Screenshot();


        @BeforeSuite
        public void commonSetup() {
            this.driverFactory = new DriverConfig();
            this.driver = this.driverFactory.getDriver();
            this.driver.navigate().to(EnvFactory.getEnvUrl());
        }


        @AfterMethod
        public void takeScreenshot(ITestResult result){
            if(ITestResult.FAILURE == result.getStatus()){
                screenshot.getScreenshot();
            }
        }

        @AfterSuite
        public void quit() {
            driver.quit();
            driverFactory.cleanWebDriver();
            System.out.println("After class executed");
        }
    }


