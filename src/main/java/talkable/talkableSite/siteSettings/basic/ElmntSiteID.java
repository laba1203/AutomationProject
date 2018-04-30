package talkable.talkableSite.siteSettings.basic;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSiteID extends AbstractElement{
    private static final By locator = By.xpath("//input[@name = 'cached_slug']");

    ElmntSiteID(){
        setWebElement(locator);
    }

}
