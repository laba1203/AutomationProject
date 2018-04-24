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

    private WebDriver getLocalChromeDriver(){
        return new ChromeDriver();
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

//    public static WebDriverWait getExplicitWait(){
//        return getCustomWait(15, 500);
//    }

//    public static WebDriverWait getCustomWait(long timeOutInSeconds, long sleepInMillis) {
//        if(wait == null){
//            wait = new WebDriverWait(getDriver(), timeOutInSeconds, sleepInMillis);
//        }
//        return wait;
//    }

//    private void setDefaultImplicitlyWait(){
//        driver.manage().timeouts().implicitlyWait(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(DEFAULT_PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
//    }

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
