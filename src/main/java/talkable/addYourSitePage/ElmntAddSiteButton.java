package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntAddSiteButton extends AbstractElement {
    private By addSiteLocator = By.cssSelector("input[value = 'Add site']");

    public ElmntAddSiteButton(){
        setWebElement(addSiteLocator);
    }
}
