package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        driver.switchTo().frame(frameElement);
        Log.frameSwitchedMsg(this);
    }

    protected void switchToParentFrame(){
        driver.switchTo().parentFrame();
        Log.frameSwitchedMsg();
    }
}
