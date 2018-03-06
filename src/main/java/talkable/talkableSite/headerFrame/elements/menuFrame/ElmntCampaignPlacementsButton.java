package talkable.talkableSite.headerFrame.elements.menuFrame;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignPlacementsButton extends AbstractElement {

    private static final By createSiteButtonLocator = By.linkText("Campaign Placements");

    ElmntCampaignPlacementsButton(){
        setWebElement(createSiteButtonLocator);
    }
}
