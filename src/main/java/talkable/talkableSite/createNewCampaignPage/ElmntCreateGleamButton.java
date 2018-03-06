package talkable.talkableSite.createNewCampaignPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateGleamButton extends AbstractElement {

    private final static By locator = By.cssSelector("div[aria-expanded='true'] form[action$='gleam'] > input[value='Create']");

//    private final static By locator = By.cssSelector("div[aria-expanded='true'] > div > ul > li > div > form[action$='widget'] > input[value='Create']");

    ElmntCreateGleamButton(){
        setWebElement(locator);
    }
}
