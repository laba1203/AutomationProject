package talkable.campaign.pages.campaignNavigationMenu.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntCampaignType extends AbstractElement{
    private static final By locator = By.cssSelector(".campaign-type>span");

    public ElmntCampaignType(){
        setWebElement(locator);
    }
}
