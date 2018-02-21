package talkable.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignStatusField extends AbstractElement{
    private static final By locator = By.className("Campaign-management-status-text");

    ElmntCampaignStatusField(){
        setWebElement(locator);
    }
}
