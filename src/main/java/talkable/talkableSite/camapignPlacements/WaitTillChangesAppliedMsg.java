package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class WaitTillChangesAppliedMsg extends AbstractElement{
    private static final By locator = By.xpath("//*[contains(text(), 'Latest changes have been saved recently. It can take up to')]");

    WaitTillChangesAppliedMsg(){
        setWebElement(locator);
    }
}
