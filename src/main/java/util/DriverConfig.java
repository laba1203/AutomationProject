package util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import util.logging.Log;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class DriverConfig {

    //URL for selenium
    private static final String SELENOID_URL = "http://selenoid.tkbl:4444//wd/hub";

    private WebDriver driver;

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /*The flag indicates whether the test will be executed on Local or Remote machine.
    !!! Please note always put the flag to TRUE before creation of pull request to origin/master !!!*/
    private static final boolean remoteExecution = true;

    @Parameters()
    private void setNewLocalDriver()
    {
        System.out.println("LOG - Util: Start creation of new Local WebDriver");
        final File file = new File(PropertyLoader.loadProperty("path.mac.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());

        tlDriver.set(new ChromeDriver());

        WaitFactory.setDefaultImplicitlyWait();

        System.out.println("LOG - Util: New Local WebDriver is created");

    }



    private static void setNewRemoteDriver(){
        System.out.println("LOG - Util: Start creation of new Remote WebDriver. Thread ID: <" + Thread.currentThread().getId() + ">");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("66.0");

//        capabilities.setBrowserName("firefox");
//        capabilities.setVersion("60.0");
        capabilities.setCapability("enableVNC", true);

        URL url = null;
        try {
             url = new URL(SELENOID_URL);
        } catch (MalformedURLException e) {
            System.err.println("Exception found");
            e.printStackTrace();
        }
        WebDriver driver = new RemoteWebDriver(
                    url,
                    capabilities
            );
        tlDriver.set(driver);
        resizeBrowser(tlDriver.get());

        System.out.println("LOG - Util: New Remote WebDriver created. Thread ID: <" + Thread.currentThread().getId() + ">");
    }

    public static WebDriver getDriver(){
        if(tlDriver.get() != null){
            return tlDriver.get();
        }else {
            Assert.fail("FAILED in DriverConfig. getting browser when it was not created. Thread <" + Thread.currentThread().getId() + ">");
            return null;
        }

//        if (tlDriver.get() == null){
//            if(remoteExecution) {
//                DriverConfig.setNewRemoteDriver(); //for remote testing
//                WaitFactory.setDefaultImplicitlyWait();
//            }else {
//                new DriverConfig().setNewLocalDriver(); //for local testing
//                WaitFactory.setDefaultImplicitlyWait();
//            }
//        }
//        System.out.println("LOG - Util: Driver assigned to Thread <" + Thread.currentThread().getId() + ">");
//        return tlDriver.get();
    }

    public static void createDriver(){
        if(remoteExecution) {
            DriverConfig.setNewRemoteDriver(); //for remote testing
            WaitFactory.setDefaultImplicitlyWait();
        }else {
            new DriverConfig().setNewLocalDriver(); //for local testing
            WaitFactory.setDefaultImplicitlyWait();
        }
    }

    public static void cleanWebDriver(){
//        WaitFactory.cleanWait();
        tlDriver.remove();
    }

    private static void resizeBrowser(WebDriver driver) {
        Dimension d = new Dimension(1300,1200);
//Resize current window to the set dimension
        driver.manage().window().setSize(d);
        tlDriver.set(driver);
    }

    //to be refactored

    public static String switchToUnknownWindow(String parentHandle){
        String switchedChildHandle = null;
        for(String childHandle : getDriver().getWindowHandles()){
            switchToUnknownWindow(parentHandle, childHandle);
            switchedChildHandle = childHandle;
        }
        return switchedChildHandle;
    }

    private static void switchToUnknownWindow(String parentHandle, String childHandle){
        if (!childHandle.equals(parentHandle)){
            getDriver().switchTo().window(childHandle);
            Log.switchedToWindowMsg(childHandle);
        }
    }

    public static void switchToWindow(String windowHandle){
        getDriver().switchTo().window(windowHandle);
        Log.switchedToWindowMsg(windowHandle);
    }



}
