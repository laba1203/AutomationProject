package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntModerateProfileButton extends AbstractElement{
    private static final By locator = By.xpath("//label[@for = 'fraud_profile_moderate']");

    ElmntModerateProfileButton(){
        setWebElement(locator);
    }
}
