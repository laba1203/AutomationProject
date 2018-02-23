package talkable.talkableSite.campaign.pages.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntViewOfferButton extends AbstractElement{

    private static final By locator = By.cssSelector("input[value='View Offer']");

    ElmntViewOfferButton(){
        setWebElement(locator);
    }

}
