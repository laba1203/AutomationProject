package talkable.campaign.pages.campaignNavigationMenu.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CampaignStatusField extends AbstractElement{
    private static final By locator = By.className("Campaign-management-status-text");

    public CampaignStatusField(){
        setWebElement(locator);
    }
}
