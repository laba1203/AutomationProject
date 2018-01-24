package talkable.campaign.pages.campaignNavigationMenu.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class DeactivateCampaignButton extends AbstractElement{
    private static final By locator = By.linkText("Deactivate Campaign");

    public DeactivateCampaignButton(){
        setWebElement(locator);
    }
}
