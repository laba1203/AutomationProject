package talkable.talkableSite.reports.purchases.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntVisitorUUIDInput extends AbstractElement{

    private static final By locator = By.cssSelector("input[name='origin[visitor_uuid]']");

    ElmntVisitorUUIDInput(){
        setWebElement(locator);
    }

}
