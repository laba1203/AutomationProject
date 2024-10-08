package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSaveChangesButton extends AbstractElement {
    private static final By locator = By.xpath("//*[text() = 'Save Changes']");

    ElmntSaveChangesButton(){
        setWebElement(locator);
    }
}
