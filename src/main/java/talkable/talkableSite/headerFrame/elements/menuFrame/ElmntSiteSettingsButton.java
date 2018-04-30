package talkable.talkableSite.headerFrame.elements.menuFrame;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSiteSettingsButton extends AbstractElement {

    private static final By createSiteButtonLocator = By.xpath("//*[text() = 'Site Settings']");

    ElmntSiteSettingsButton(){
        setWebElement(createSiteButtonLocator);
    }
}
