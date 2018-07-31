package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.logging.Log;

public abstract class AbstractTalkableFrame extends AbstractElementsContainer{
    private By frameLctr;

    protected void setWebElement(By locator){
        frameLctr = locator;
        getFrameWebElement();
    }

    protected WebElement getFrameWebElement(){
        return driver.findElement(frameLctr);
    }

    protected void switchToThisFrame(){
        wait.until(ExpectedConditions.visibilityOf(getFrameWebElement()));
        driver.switchTo().frame(getFrameWebElement());

        Log.frameSwitchedMsg(this);
    }

    protected void switchToParentFrame(){
        driver.switchTo().parentFrame();
        Log.frameSwitchedMsg();
    }

}
