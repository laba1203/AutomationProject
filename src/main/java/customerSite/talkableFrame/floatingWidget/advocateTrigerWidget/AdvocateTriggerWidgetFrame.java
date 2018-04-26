package customerSite.talkableFrame.floatingWidget.advocateTrigerWidget;

import abstractObjects.AbstractTalkableFrame;
import customerSite.talkableFrame.floatingWidget.advocateSignupPage.AdvocateSignupPageFW;
import org.openqa.selenium.By;

public class AdvocateTriggerWidgetFrame extends AbstractTalkableFrame{
    private static final By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe']");
    private FloatingWidgetButton button;

    public AdvocateTriggerWidgetFrame(){
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
