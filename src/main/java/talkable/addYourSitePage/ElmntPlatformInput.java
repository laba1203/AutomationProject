package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntPlatformInput extends AbstractElement {
    private By platformLocator = By.id("site_platform");

    public ElmntPlatformInput(){
        setWebElement(platformLocator);
    }
}
