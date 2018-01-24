package talkable.campaign.pages.campaignNavigationMenu.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class LaunchDeactivateCampaignButton extends AbstractElement{
//    private static final By locator = By.linkText("Launch Campaign");
    private static final By locator = By.cssSelector(".subnav-actions .btn-success" );

    public LaunchDeactivateCampaignButton(){
        setWebElement(locator);
    }
}
