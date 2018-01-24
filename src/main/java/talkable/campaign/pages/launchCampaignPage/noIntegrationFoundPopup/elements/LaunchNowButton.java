package talkable.campaign.pages.launchCampaignPage.noIntegrationFoundPopup.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class LaunchNowButton extends AbstractElement{

    private static final By locator = By.cssSelector(".is-popup.is-shown .is-success");

    public LaunchNowButton(){
        setWebElement(locator);
    }
}
