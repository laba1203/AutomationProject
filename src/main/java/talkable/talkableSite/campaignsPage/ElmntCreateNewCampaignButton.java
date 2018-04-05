package talkable.talkableSite.campaignsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateNewCampaignButton extends AbstractElement{
    private static final By locator = By.cssSelector("[data-action='Create New Campaign Dropdown']");

    ElmntCreateNewCampaignButton(){
        setWebElement(locator);
    }
}
