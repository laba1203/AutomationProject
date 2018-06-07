package talkable.talkableSite.reports.purchases.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntViewOfferButton extends AbstractElement{

    private static final By locator = By.cssSelector("input[value='View Offer']");

    ElmntViewOfferButton(){
        setWebElement(locator);
    }

}
