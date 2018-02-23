package talkable.talkableSite.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntLaunchDeactivateCampaignButton extends AbstractElement{
//    private static final By locator = By.linkText("Launch Campaign");
    private static final By locator = By.cssSelector(".subnav-actions .btn" );

    ElmntLaunchDeactivateCampaignButton(){
        setWebElement(locator);
    }
}
