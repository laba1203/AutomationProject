package util;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitFactory {

    private static WebDriverWait wait;

    private static final long DEFAULT_TIME_OUT = 20;
    private static final long DEFAULT_PAGE_LOAD_TIME_OUT = 40;

    static void setDefaultImplicitlyWait(){
        setImplicitWait(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        DriverConfig.getDriver().manage().timeouts().pageLoadTimeout(DEFAULT_PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
    }

    static void cleanWait(){
        wait = null;
    }

    private static void setImplicitWait(long l, TimeUnit timeUnit){
        DriverConfig.getDriver().manage().timeouts().implicitlyWait(l, timeUnit);
    }

    public static WebDriverWait getCustomWait(long timeOutInSeconds, long sleepInMillis) {
        if(wait == null){
            wait = new WebDriverWait(DriverConfig.getDriver(), timeOutInSeconds, sleepInMillis);
        }
        return wait;
    }

    public static WebDriverWait getExplicitWait(){
        return getCustomWait(10, 500);
    }

    public static void waitInvisibilityOfElementWithText(By by, String text){
        setImplicitWait(2, TimeUnit.SECONDS);
        try{
            wait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
        }
        finally {
            setDefaultImplicitlyWait();
        }
    }
    //wait.until(ExpectedConditions.invisibilityOfElementWithText(currentPageLctr, currentPageText))



}
