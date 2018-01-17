package talkable.createNewCampaignPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CreateStandaloneButton extends AbstractElement {

    private final static By locator = By.cssSelector("div[aria-expanded='true'] form[action$='inline'] > input[value='Create']");

    public CreateStandaloneButton(){
        setWebElement(locator);
    }
}
