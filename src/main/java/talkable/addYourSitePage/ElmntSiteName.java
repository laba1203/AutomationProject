package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntSiteName extends AbstractElement {
    private By siteNameLocator = By.id("site_name");

    public ElmntSiteName(){
        setWebElement(siteNameLocator);
    }
}
