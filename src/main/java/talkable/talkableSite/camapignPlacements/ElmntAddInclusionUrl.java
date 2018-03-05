package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAddInclusionUrl extends AbstractElement {
    private static final By locator = By.linkText("Add inclusion URL");

    ElmntAddInclusionUrl(){
        setWebElement(locator);
    }
}
