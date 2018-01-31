package customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget;

import abstractObjects.AbstractTalkableFrame;
import org.openqa.selenium.By;

public class AdvocateTriggerWidgetFrame extends AbstractTalkableFrame{
    private By frameLocator = By.cssSelector("iframe[name='talkable-offer-iframe']");
    private FloatingWidgetButton button;

    public AdvocateTriggerWidgetFrame(){
        setWebElement(frameLocator);
    }

    public void click(){
        switchToThisFrame();
        button = new FloatingWidgetButton();
        button.click();
        switchToParentFrame();

    }




}
