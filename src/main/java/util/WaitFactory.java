package util;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitFactory {

    private WebDriverWait wait;

    private static final long DEFAULT_TIME_OUT = 20;
    private static final long DEFAULT_PAGE_LOAD_TIME_OUT = 40;

    static void setDefaultImplicitlyWait(){
        setImplicitWait(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        DriverConfig.getDriver().manage().timeouts().pageLoadTimeout(DEFAULT_PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
    }

    void cleanWait(){
        wait = null;
    }

    private static void setImplicitWait(long l, TimeUnit timeUnit){
        DriverConfig.getDriver().manage().timeouts().implicitlyWait(l, timeUnit);
    }

    public WebDriverWait getCustomWait(long timeOutInSeconds, long sleepInMillis) {
        if(wait == null){
            wait = new WebDriverWait(DriverConfig.getDriver(), timeOutInSeconds, sleepInMillis);
        }
        return wait;
    }

    public WebDriverWait getExplicitWait(){
        return getCustomWait(10, 500);
    }

    public void waitInvisibilityOfElementWithText(By by, String text){
        setImplicitWait(2, TimeUnit.SECONDS);
        try{
            wait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
        }
        finally {
            setDefaultImplicitlyWait();
        }
    }

    public void waitUntilTextToBePresentInElement(By by, String text, long timeoutInSeconds){
        setImplicitWait(2, TimeUnit.SECONDS);
        try {
            getCustomWait(timeoutInSeconds, 500)
                    .until(ExpectedConditions.textToBePresentInElement(
                            DriverConfig.getDriver().findElement(by),
                            text
                            )
                    );
        }
        finally {
            setDefaultImplicitlyWait();
        }
    }

    public void waitUntilVisibilityOfElementLocated(By by, long timeoutInSeconds){
        setImplicitWait(2, TimeUnit.SECONDS);
        try{
            getCustomWait(timeoutInSeconds, 500).until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        finally {
            setDefaultImplicitlyWait();
        }
    }

    public static List<WebElement> waitChildElementsForElement(Element parentElement, By childrenLocator){
        setImplicitWait(2, TimeUnit.SECONDS);
        try{
            return parentElement.getWebElement().findElements(childrenLocator);
        }
        finally {
            setDefaultImplicitlyWait();
        }

    }


    //wait.until(ExpectedConditions.invisibilityOfElementWithText(currentPageLctr, currentPageText))



}
