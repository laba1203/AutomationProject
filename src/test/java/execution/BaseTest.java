package execution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.DriverConfig;
import util.EnvFactory;
import util.Screenshot;
import util.WaitFactory;
import util.logging.Log;

import java.lang.reflect.Method;

public class BaseTest {

        public WebDriver driver;
        private Screenshot screenshot = new Screenshot();


        //setup driver and open Talkable site.
        @BeforeSuite
        public void commonSetup() {
            Log.logRecord("Class name: " + this.getClass().getName());
            this.driver = DriverConfig.getDriver();
            try {
                this.driver.navigate().to(EnvFactory.getEnvUrl());
            }catch (org.openqa.selenium.TimeoutException e){
                Log.logRecord("Timeout received during navigation to Env URL in BaseTest.commonSetup(). Second attempt to open URL.");
                DriverConfig.getDriver().quit();
                DriverConfig.cleanWebDriver();
                this.driver = DriverConfig.getDriver();
                this.driver.navigate().to(EnvFactory.getEnvUrl());
                Log.logRecord("Successfully opened URL from the second attempt");
            }
            System.out.println("*** DEBAG: Before suite executed in Base Test of class: " + getClass().getName() + " ***\r\n");
        }



        @BeforeClass
        public void verifyDriver() {
            if(driver==null){
                commonSetup();
                System.out.println("DEBAG: WebDriver assigned for particular class: " + getClass().getName());
            }

        }


         @BeforeMethod(alwaysRun = true)
        public void logMethodName(Method method) {
            System.err.println("\r\n***  TEST-LOG: Method name: <" + method.getName() + ">  ***");
        }


        @AfterMethod
        public void takeScreenshot(ITestResult result){
            if(ITestResult.FAILURE == result.getStatus()){
                screenshot.makeScreenshot(); //TODO: re-work capturing screenshots
                System.out.println("URL on fail: " + driver.getCurrentUrl());
            }
        }

      @AfterSuite
        public void quit() {
            this.driver.quit();
            DriverConfig.cleanWebDriver();
            System.out.println("*** DEBAG: After Suite executed in Base Test of class: " + getClass().getName() + " ***\r\n");
        }
    }


