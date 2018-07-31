package execution;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.*;
import util.logging.Log;

import java.lang.reflect.Method;
import java.util.Arrays;

public class BaseTest {

        public WebDriver driver;

        //setup driver and open Talkable site.
        @BeforeClass
        public void commonSetup() {
            Log.logRecord("Class name: " + this.getClass().getName());
            DriverConfig.createDriver();
            this.driver = DriverConfig.getDriver();
            try {
                this.driver.navigate().to(EnvFactory.getEnvUrl());
            }catch (org.openqa.selenium.TimeoutException e){
                Log.logRecord("Timeout received during navigation to Env URL in BaseTest.commonSetup(). Second attempt to open URL.");
                DriverConfig.quitAndRemoveWebDriver();
                DriverConfig.createDriver();
                this.driver = DriverConfig.getDriver();
                try {
                    this.driver.navigate().to(EnvFactory.getEnvUrl());
                }catch (org.openqa.selenium.TimeoutException e2){
                    Log.logRecord("The second Timeout received during navigation to Env URL in BaseTest.commonSetup(). Closing web driver...\r\n"
                            + e2.getMessage() +"\r\n"
                            + Arrays.toString(e2.getStackTrace())
                    );
                    DriverConfig.quitAndRemoveWebDriver();
                    e2.printStackTrace();
                }
                Log.logRecord("Successfully opened URL from the second attempt");
            }
            System.out.println("*** DEBAG: Before class executed in Base Test of class: " + getClass().getName() + " ***\r\n");
        }


        @BeforeMethod(alwaysRun = true)
        public void logMethodName(Method method) {
            System.err.println("\r\n***  TEST-LOG: Method name: <" + method.getName() + ">  ***");
        }


//        @AfterMethod
//        public void takeScreenshot(ITestResult result){
////            if(ITestResult.FAILURE == result.getStatus()){
////                screenshot.makeScreenshot();
////                System.out.println("URL on fail: " + driver.getCurrentUrl());
////            }
////            if(ITestResult.SUCCESS == result.getStatus()){
////                Log.testPassed("BaseTest. Test method: " + metho)
////            }
//        }

      @AfterClass
        public void quit() {
            DriverConfig.quitAndRemoveWebDriver();
            System.out.println("*** DEBAG: After Class executed in Base Test of class: " + getClass().getName() + " ***\r\n");
        }
    }


