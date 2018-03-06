package talkable.talkableSite.headerFrame.elements.menuFrame;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateNewCampaignButton extends AbstractElement {

    private static final By createCampaignButtonLocator = By.linkText("Create New Campaign");

    ElmntCreateNewCampaignButton(){
        setWebElement(createCampaignButtonLocator);
    }
}
