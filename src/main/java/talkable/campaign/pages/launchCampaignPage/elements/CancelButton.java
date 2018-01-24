package talkable.campaign.pages.launchCampaignPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CancelButton extends AbstractElement{

    private static final By locator = By.cssSelector("form[id='edit_campaign'] a");

    public CancelButton(){
        setWebElement(locator);
    }
}
