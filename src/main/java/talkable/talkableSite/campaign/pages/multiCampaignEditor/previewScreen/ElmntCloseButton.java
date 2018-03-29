package talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCloseButton extends AbstractElement{
    private static final By locator = By.cssSelector(".modal-close");

    ElmntCloseButton(){
        setWebElement(locator);
    }
}
