package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignDescriptionInput extends AbstractElement{

    private static final By locator = By.cssSelector("[name = 'description']");

    ElmntCampaignDescriptionInput(){
        setWebElement(locator);
    }

}
