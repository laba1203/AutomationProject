package customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget;

import abstractObjects.AbstractTalkableFrame;
import customerSite.talkableFrame.floatingWidgete.advocateSharePage.AdvocateSharePage;
import customerSite.talkableFrame.floatingWidgete.advocateSignupPage.AdvocateSignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdvocateTriggerWidgetFrame extends AbstractTalkableFrame{
    private By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe']");
    private FloatingWidgetButton button;

    public AdvocateTriggerWidgetFrame(){
        setWebElement(frameLocator);
    }

    public AdvocateSignupPage click(){
//        wait.until(ExpectedConditions.visibilityOf())
        switchToThisFrame();
        button = new FloatingWidgetButton();
        button.click();
        switchToParentFrame();
        return new AdvocateSignupPage();

    }




}
