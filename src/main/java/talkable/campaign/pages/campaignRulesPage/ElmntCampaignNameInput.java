package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignNameInput extends AbstractElement{

    private static final By locator = By.cssSelector("[name = 'name']");

    ElmntCampaignNameInput(){
        setWebElement(locator);
    }

}
