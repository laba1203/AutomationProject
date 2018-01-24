package talkable.headerFrame.elements.menuFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CreateNewSiteButton extends AbstractElement {

    private static final By createSiteButtonLocator = By.linkText("Create New Site");

    public CreateNewSiteButton(){
        setWebElement(createSiteButtonLocator);
    }
}
