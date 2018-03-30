package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAddSiteButton extends AbstractElement {
    private static final By addSiteLocator = By.cssSelector("input[value = 'Add site']");

    ElmntAddSiteButton(){
        setWebElement(addSiteLocator);
    }
}
