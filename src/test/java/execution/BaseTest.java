package execution;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.DriverConfig;
import util.EnvFactory;
import util.Screenshot;

import java.lang.reflect.Method;

public class BaseTest {
        public DriverConfig driverFactory;
        public WebDriver driver;
        private Screenshot screenshot = new Screenshot();


        @BeforeSuite
        public void commonSetup() {

            this.driverFactory = new DriverConfig();
            this.driver = this.driverFactory.getDriver();
            this.driver.navigate().to(EnvFactory.getEnvUrl());
            System.out.println("DEBAG: Before suite executed");
        }



        @BeforeClass
        public void verifyDriver() {
            if(driver==null){
                commonSetup();
                System.out.println("DEBAG: WebDriver assigned for particular class: " + getClass().getName());
            }

        }

        //to be tested:
         @BeforeMethod(alwaysRun = true)
        public void logMethodName(Method method) {
            System.err.println("\r\nDEBAG: Method name: <" + method.getName() + ">");
        }


        @AfterMethod
        public void takeScreenshot(ITestResult result){
            if(ITestResult.FAILURE == result.getStatus()){
                screenshot.getScreenshot();
            }
            System.out.println("DEBAG: Tried to capture screenshot");
        }

        @AfterSuite
        public void quit() {
            this.driver.quit();
            this.driverFactory.cleanWebDriver();
            System.out.println("DEBAG: After class executed");
        }
    }


