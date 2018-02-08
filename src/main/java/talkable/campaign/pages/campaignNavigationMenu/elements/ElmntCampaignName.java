package talkable.campaign.pages.campaignNavigationMenu.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntCampaignName extends AbstractElement{
    private static final By locator = By.cssSelector(".Campaign-management-header-title");

    public ElmntCampaignName(){
        setWebElement(locator);
    }
}
