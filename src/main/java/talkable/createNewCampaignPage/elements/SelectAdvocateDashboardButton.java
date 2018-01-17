package talkable.createNewCampaignPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class SelectAdvocateDashboardButton extends AbstractElement {

    private final static By locator = By.cssSelector(".is-dashboard .js-campaign-new-select");

    public SelectAdvocateDashboardButton(){
        setWebElement(locator);
    }
}
