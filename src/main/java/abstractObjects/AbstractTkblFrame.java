package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import util.DriverConfig;
import util.logging.Log;

public abstract class AbstractTkblFrame extends AbstractElementsContainer{
    private By frameLctr;

    protected void setWebElement(By locator){
        frameLctr = locator;
        getFrameWebElement();
    }

    protected WebElement getFrameWebElement(){
        if(frameLctr == null){
            Log.debagRecord("Driver = " + DriverConfig.getDriver());
            Assert.fail("Frame locator is null.");
        }
        return driver.findElement(frameLctr);
    }

    protected void switchToThisFrame(){
        try {
            wait.until(
                    ExpectedConditions
                            .visibilityOf(getFrameWebElement())
            );
        }catch (StaleElementReferenceException e){
            Log.debagRecord("StaleElementReferenceException was catch during execution of AbstractTkblFrame.switchToThisFrame() in class " + this.getClass().getName() + ". " +
                    "Trying to getFrameWebElement() once again...");
            wait.until(ExpectedConditions.visibilityOf(getFrameWebElement()));
        }
        driver.switchTo().frame(getFrameWebElement());

        Log.frameSwitchedMsg(this);
    }

    protected void switchToParentFrame(){
        driver.switchTo().parentFrame();
        Log.frameSwitchedMsg();
    }

}
