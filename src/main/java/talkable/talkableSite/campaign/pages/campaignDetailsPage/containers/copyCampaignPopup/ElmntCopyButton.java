package talkable.talkableSite.campaign.pages.campaignDetailsPage.containers.copyCampaignPopup;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCopyButton extends AbstractElement{
    private static final By locator = By.cssSelector("input[value='Copy']");

    ElmntCopyButton(){
        setWebElement(locator);
    }

}
