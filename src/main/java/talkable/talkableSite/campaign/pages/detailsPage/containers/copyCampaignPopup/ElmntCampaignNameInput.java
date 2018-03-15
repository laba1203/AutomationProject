package talkable.talkableSite.campaign.pages.detailsPage.containers.copyCampaignPopup;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignNameInput extends AbstractElement{
    private static final By locator = By.cssSelector("input[name='name']");

    ElmntCampaignNameInput(){
        setWebElement(locator);
    }

}
