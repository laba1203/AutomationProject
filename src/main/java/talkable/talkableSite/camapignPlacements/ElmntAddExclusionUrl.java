package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAddExclusionUrl extends AbstractElement {
    private static final By locator = By.linkText("Add exclusion URL");

    ElmntAddExclusionUrl(){
        setWebElement(locator);
    }
}
