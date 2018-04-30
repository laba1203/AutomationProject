package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntProfileDescription extends AbstractElement{
    private static final By locator = By.xpath("//div[@style = 'display: block;']//div[@class = 'Fraud-config-description']/p");

    ElmntProfileDescription(){
        setWebElement(locator);
    }
}
