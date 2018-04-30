package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSaveChangesButton extends AbstractElement{
    private static final By locator = By.xpath("//button[text() = 'Save Changes']");

    ElmntSaveChangesButton(){
        setWebElement(locator);
    }
}
