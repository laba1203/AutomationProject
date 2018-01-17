package talkable.addYourSitePage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class SiteName extends AbstractElement {
    private By siteNameLocator = By.id("site_name");

    public SiteName(){
        setWebElement(siteNameLocator);
    }
}
