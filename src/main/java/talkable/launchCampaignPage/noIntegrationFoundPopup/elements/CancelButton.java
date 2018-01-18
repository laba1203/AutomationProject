package talkable.launchCampaignPage.noIntegrationFoundPopup.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CancelButton extends AbstractElement{

    private static final By locator = By.linkText("Cancel");

    public CancelButton(){
        setWebElement(locator);
    }
}
