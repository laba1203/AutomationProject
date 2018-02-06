package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.concurrent.TimeUnit;



public class DriverConfig {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final long DEFAULT_TIME_OUT = 15;

    @Parameters
    private WebDriver setNewDriver()
    {
        final File file = new File(PropertyLoader.loadProperty("path."+getOS()+".webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
        driver = new ChromeDriver();

        setImplicitlyWait();

        return driver;

    }

    private String getOS(){
        System.out.println("Test is running on" + System.getProperty("os.name"));
        return System.getProperty("os.name").toLowerCase().substring(0,3);
    }




    public WebDriver getDriver(){
        if (driver == null){
            driver = new DriverConfig().setNewDriver();
        }
        return driver;
    }

    public WebDriverWait getExplicitWait(){
        if(wait == null){
            wait = new WebDriverWait(driver, 2, 500);
        }
        return wait;
    }

    private void setImplicitlyWait(){
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);

    }

    public void cleanWebDriver(){
        driver = null;
    }


}
