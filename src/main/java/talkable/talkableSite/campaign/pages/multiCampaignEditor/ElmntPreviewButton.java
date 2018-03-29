package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntPreviewButton extends AbstractElement{
    private static final By locator = By.cssSelector(".edit-field-grid > .base-btn");

    ElmntPreviewButton(){
        setWebElement(locator);
    }
}
