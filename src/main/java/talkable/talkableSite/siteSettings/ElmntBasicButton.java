package talkable.talkableSite.siteSettings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntBasicButton extends AbstractElement{
    private static final By locator = By.xpath("//a[contains(@class, 'ac-site-navigation-basic')]");

    ElmntBasicButton(){
        setWebElement(locator);
    }
}
