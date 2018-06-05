package talkable.talkableSite.siteSettings.basic;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntSiteName extends AbstractElement {
    private static final By locator = By.id("site-name");
    ElmntSiteName() {setWebElement(locator);}
}
