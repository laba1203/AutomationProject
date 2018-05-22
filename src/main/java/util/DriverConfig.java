package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import util.logging.Log;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class DriverConfig {

    //URL for selenium
    private static final String SELENOID_URL = "http://selenoid.production:4444/wd/hub";

    private static WebDriver driver;

    @Parameters()
    private WebDriver setNewLocalDriver()
    {
        System.out.println("LOG - Util: Start creation of new Local WebDriver");
        final File file = new File(PropertyLoader.loadProperty("path.mac.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());

        driver = new ChromeDriver(); //launch local webDriver

        WaitFactory.setDefaultImplicitlyWait();

        System.out.println("LOG - Util: New Local WebDriver is created");

        return driver;
    }



    private WebDriver setNewRemoteDriver(){

        System.out.println("LOG - Util: Start creation of new Remote WebDriver");




        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("66.0");

//        capabilities.setBrowserName("firefox");
//        capabilities.setVersion("60.0");

        driver = null;
        try {
            driver = new RemoteWebDriver(
                    new URL(SELENOID_URL),
                    capabilities
            );
            System.out.println("DEBAG: Driver created");
        } catch (MalformedURLException e) {
            System.err.println("Exception found");
            e.printStackTrace();
        }


//        final File file = new File(PropertyLoader.loadProperty("path.linux.webDriver"));
////        final File file = new File(PropertyLoader.loadProperty("path.mac.webDriver"));
//        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
//
//        //code for remote driver:
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
////        capabilities.setBrowserName("chrome");
//        try {
//            driver = new RemoteWebDriver(new URL(URL), capabilities);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        //setup default wait
        if(driver != null) {
            System.out.println("DEBAG: Implicitly creation started");
            WaitFactory.setDefaultImplicitlyWait();
            System.out.println("DEBAG: Implicitly wait created");
        }

        System.out.println("LOG - Util: New Remote WebDriver created");

        return driver;
    }

    public static WebDriver getDriver(){
        if (driver == null){
//            driver = new DriverConfig().setNewLocalDriver(); //for local testing
            driver = new DriverConfig().setNewRemoteDriver(); //for remote testing
        }
        return driver;
    }

    public static void cleanWebDriver(){
        WaitFactory.cleanWait();
        driver = null;
    }

    //to be refactored

    public static String switchToUnknownWindow(String parentHandle){
        String switchedChildHandle = null;
        for(String childHandle : driver.getWindowHandles()){
            switchToUnknownWindow(parentHandle, childHandle);
            switchedChildHandle = childHandle;
        }
        return switchedChildHandle;
    }

    private static void switchToUnknownWindow(String parentHandle, String childHandle){
        if (!childHandle.equals(parentHandle)){
            driver.switchTo().window(childHandle);
            Log.switchedToWindowMsg(childHandle);
        }
    }

    public static void switchToWindow(String windowHandle){
        driver.switchTo().window(windowHandle);
        Log.switchedToWindowMsg(windowHandle);
    }



}
