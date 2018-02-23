package talkable.talkableSite.createNewCampaignPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class SelectInviteButton extends AbstractElement {

    private final static By locator = By.cssSelector(".is-invite .js-campaign-new-select");

    public SelectInviteButton(){
        setWebElement(locator);
    }
}
