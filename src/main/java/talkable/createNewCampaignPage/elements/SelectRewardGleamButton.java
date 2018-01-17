package talkable.createNewCampaignPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class SelectRewardGleamButton extends AbstractElement {

    private final static By locator = By.cssSelector(".is-gleam .js-campaign-new-select");

    public SelectRewardGleamButton(){
        setWebElement(locator);
    }
}
