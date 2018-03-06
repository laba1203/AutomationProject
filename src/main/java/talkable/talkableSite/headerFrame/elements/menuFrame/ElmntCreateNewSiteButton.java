package talkable.talkableSite.headerFrame.elements.menuFrame;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateNewSiteButton extends AbstractElement {

    private static final By createSiteButtonLocator = By.linkText("Create New Site");

    ElmntCreateNewSiteButton(){
        setWebElement(createSiteButtonLocator);
    }
}
