package talkable.campaign.pages.launchCampaignPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class LaunchCampaignButton extends AbstractElement{

    private static final By locator = By.cssSelector("input[name='commit']");

    public LaunchCampaignButton(){
        setWebElement(locator);
    }
}
