package talkable.talkableSite.siteSettings.basic;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntSiteURL extends AbstractElement {
    private static final By locator = By.id("site-url");
    ElmntSiteURL(){
        setWebElement(locator);
    }
}
