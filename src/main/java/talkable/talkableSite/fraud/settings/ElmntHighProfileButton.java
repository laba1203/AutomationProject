package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntHighProfileButton extends AbstractElement{
    private static final By locator = By.xpath("//label[@for = 'fraud_profile_high']");

    ElmntHighProfileButton(){
        setWebElement(locator);
    }
}
