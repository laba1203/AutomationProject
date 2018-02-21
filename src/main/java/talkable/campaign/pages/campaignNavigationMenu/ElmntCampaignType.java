package talkable.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignType extends AbstractElement{
    private static final By locator = By.cssSelector(".campaign-type>span");

    ElmntCampaignType(){
        setWebElement(locator);
    }
}
