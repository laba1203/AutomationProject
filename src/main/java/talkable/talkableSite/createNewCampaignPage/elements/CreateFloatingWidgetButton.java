package talkable.talkableSite.createNewCampaignPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CreateFloatingWidgetButton extends AbstractElement {

    private final static By locator = By.cssSelector("div[aria-expanded='true'] form[action$='widget'] > input[value='Create']");

//    private final static By locator = By.cssSelector("div[aria-expanded='true'] > div > ul > li > div > form[action$='widget'] > input[value='Create']");

    public CreateFloatingWidgetButton(){
        setWebElement(locator);
    }
}
