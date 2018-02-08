package talkable.campaign.pages.campaignDetailsPage.containers.copyCampaignPopup;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignNameInput extends AbstractElement{
    private static final By locator = By.cssSelector("input[name='name']");

    ElmntCampaignNameInput(){
        setWebElement(locator);
    }

}
