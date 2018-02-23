package talkable.talkableSite.createNewCampaignPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CreatePostPurchaseButton extends AbstractElement {

    private final static By locator = By.cssSelector("div[aria-expanded='true'] form[action$='popup'] > input[value='Create']");

    public CreatePostPurchaseButton(){
        setWebElement(locator);
    }
}
