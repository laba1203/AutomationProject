package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntPlatformInput extends AbstractElement {
    private static final By platformLocator = By.id("site_platform");

    ElmntPlatformInput(){
        setWebElement(platformLocator);
    }
}
