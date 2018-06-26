package talkable.talkableSite.reports.purchases.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntEmailInput extends AbstractElement{

    private static final By locator = By.cssSelector("input[name='origin[email]']");

    ElmntEmailInput(){
        setWebElement(locator);
    }

}
