package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.logging.Log;

public class AbstractTalkableFrame extends AbstractElementsContainer{
    private WebElement frameElement;

    protected void setWebElement(By locator){
        frameElement = driver.findElement(locator);
    }

    protected WebElement getFrameWebElement() {
        return frameElement;
    }

    protected void switchToThisFrame(){
        wait.until(ExpectedConditions.visibilityOf(frameElement));
        driver.switchTo().frame(frameElement);
        Log.frameSwitchedMsg(this);
    }

    protected void switchToParentFrame(){
        driver.switchTo().parentFrame();
        Log.frameSwitchedMsg();
    }
}
