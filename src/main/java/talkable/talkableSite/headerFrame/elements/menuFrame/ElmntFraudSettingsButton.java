package talkable.talkableSite.headerFrame.elements.menuFrame;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntFraudSettingsButton extends AbstractElement {

    private static final By locator = By.xpath("//*[text() = 'Fraud Settings']");

    ElmntFraudSettingsButton(){
        setWebElement(locator);
    }
}
