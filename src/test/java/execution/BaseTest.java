package execution;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.DriverConfig;
import util.EnvFactory;
import util.Screenshot;

import java.lang.reflect.Method;

public class BaseTest {
//        private DriverConfig driverFactory;
        public WebDriver driver;
        private Screenshot screenshot = new Screenshot();


        //setup driver and open Talkable site.
        @BeforeSuite
        public void commonSetup() {
//            this.driverFactory = new DriverConfig();
            this.driver = DriverConfig.getDriver();
            this.driver.navigate().to(EnvFactory.getEnvUrl());
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
            System.err.println("\r\nLOG: Method name: <" + method.getName() + ">");
        }


//        @AfterMethod
        public void takeScreenshot(ITestResult result){
            System.out.println("DEBAG: Method ITestResult: <" + result.getStatus() + "> for screenshots");
            if(ITestResult.FAILURE == result.getStatus()){
                screenshot.makeScreenshot();
                System.out.println("URL: " + driver.getCurrentUrl());
            }
        }

        @AfterSuite
        public void quit() {
            this.driver.quit();
            DriverConfig.cleanWebDriver();
            System.out.println("*** DEBAG: After Suite executed in Base Test of class: " + getClass().getName() + " ***\r\n");
        }
    }


