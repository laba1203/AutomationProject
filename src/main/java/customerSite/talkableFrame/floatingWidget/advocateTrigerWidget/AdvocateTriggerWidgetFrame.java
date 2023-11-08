package customerSite.talkableFrame.floatingWidget.advocateTrigerWidget;

import abstractObjects.AbstractTkblFrame;
import customerSite.talkableFrame.floatingWidget.advocateSignupPage.AdvocateSignupPageFW;
import org.openqa.selenium.By;

public class AdvocateTriggerWidgetFrame extends AbstractTkblFrame {
    private static final By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe']");
    private FloatingWidgetButton button;

    public AdvocateTriggerWidgetFrame(){
        waitFactory().waitUntilVisibilityOfElementLocated(AdvocateTriggerWidgetFrame.getFrameLocator(), 3);
        setWebElement(frameLocator);
    }



    public AdvocateSignupPageFW click(){
//        wait.until(ExpectedConditions.visibilityOf())
        switchToThisFrame();
        button = new FloatingWidgetButton();
        button.click();
        switchToParentFrame();
        return new AdvocateSignupPageFW();

    }

    public static By getFrameLocator(){
        return frameLocator;
    }




}
