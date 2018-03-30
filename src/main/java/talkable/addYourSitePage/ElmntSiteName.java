package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSiteName extends AbstractElement {
    private static final By siteNameLocator = By.id("site_name");

    ElmntSiteName(){
        setWebElement(siteNameLocator);
    }
}
