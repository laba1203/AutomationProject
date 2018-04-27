package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntProfileNameHeader extends AbstractElement{
    private static final By locator = By.xpath("//div[@style = 'display: block;']/h3[@class = 'Fraud-config-sub-headline']");

    ElmntProfileNameHeader(){
        setWebElement(locator);
    }
}
