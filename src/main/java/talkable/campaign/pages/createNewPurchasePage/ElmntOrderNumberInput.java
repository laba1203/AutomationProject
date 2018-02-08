package talkable.campaign.pages.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntOrderNumberInput extends AbstractElement{

    private static final By locator = By.cssSelector("input[name='origin[order_number]']");

    ElmntOrderNumberInput(){
        setWebElement(locator);
    }

}
