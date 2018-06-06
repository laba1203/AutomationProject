package talkable.talkableSite.camapignPlacements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntPlacementName extends AbstractElement {
    private static final By locator = By.xpath("//*[@class='Routes-head-form']//input");

    ElmntPlacementName(){
        setWebElement(locator);
    }
}
