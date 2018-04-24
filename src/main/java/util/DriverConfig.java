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
    private static final String URL = "http://localhost:4444/wd/hub";

    private static WebDriver driver;


    @Parameters()
    private WebDriver setNewLocalDriver()
    {
        final File file = new File(PropertyLoader.loadProperty("path.mac.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());

        driver = new ChromeDriver(); //launch local webDriver

        WaitFactory.setDefaultImplicitlyWait();

        return driver;
    }



    private WebDriver setNewRemoteDriver(){
//        final File file = new File(PropertyLoader.loadProperty("path.linux.webDriver"));
        final File file = new File(PropertyLoader.loadProperty("path.mac.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());

        //code for remote driver:
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL(URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //
        WaitFactory.setDefaultImplicitlyWait();

        return driver;
    }

    public static WebDriver getDriver(){
        if (driver == null){
            driver = new DriverConfig().setNewLocalDriver(); //for local testing
//            driver = new DriverConfig().setNewRemoteDriver(); //for remote testing
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
