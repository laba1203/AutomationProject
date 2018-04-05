package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import util.logging.Log;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;



public class DriverConfig {

    //URL for selenium
    private static final String URL = "http://localhost:4444/wd/hub";

    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final long DEFAULT_TIME_OUT = 20;

    @Parameters()
    private WebDriver setNewLocalDriver()
    {
        final File file = new File(PropertyLoader.loadProperty("path."+getOS()+".webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());

        driver = new ChromeDriver(); //launch local webDriver

        setImplicitlyWait();

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
        setImplicitlyWait();

        return driver;
    }

    private String getOS(){
        System.out.println("Test is running on " + System.getProperty("os.name"));
        return System.getProperty("os.name").toLowerCase().substring(0,3);
    }


    public WebDriver getDriver(){
        if (driver == null){
            driver = new DriverConfig().setNewLocalDriver(); //for local testing
//            driver = new DriverConfig().setNewRemoteDriver(); //for remote testing
        }
        return driver;
    }

    public WebDriverWait getExplicitWait(){
        if(wait == null){
            wait = new WebDriverWait(getDriver(), 15, 500);
        }
        return wait;
    }

    private void setImplicitlyWait(){
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
    }

    public void cleanWebDriver(){
        wait = null;
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
