package talkable.addYourSitePage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class AddSiteButton extends AbstractElement {
    private By addSiteLocator = By.cssSelector("input[value = 'Add site']");

    public AddSiteButton(){
        setWebElement(addSiteLocator);
    }
}
