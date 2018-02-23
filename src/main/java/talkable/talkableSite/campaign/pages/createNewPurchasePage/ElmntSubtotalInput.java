package talkable.talkableSite.campaign.pages.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSubtotalInput extends AbstractElement{

    private static final By locator = By.cssSelector("input[name='origin[subtotal]']");

    ElmntSubtotalInput(){
        setWebElement(locator);
    }

}
