package talkable.addYourSitePage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class PlatformInput extends AbstractElement {
    private By platformLocator = By.id("site_platform");

    public PlatformInput(){
        setWebElement(platformLocator);
    }
}
