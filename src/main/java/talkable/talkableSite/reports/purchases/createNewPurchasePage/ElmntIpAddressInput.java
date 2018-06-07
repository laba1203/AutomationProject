package talkable.talkableSite.reports.purchases.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntIpAddressInput extends AbstractElement{

    private static final By locator = By.cssSelector("input[name='origin[ip_address]']");

    ElmntIpAddressInput(){
        setWebElement(locator);
    }

}
