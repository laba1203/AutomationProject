package talkable.headerFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class SelectedSiteElement extends AbstractElement{
    private static final By selectedSiteLocator = By.cssSelector(".sites-select > .chosen-container > .chosen-single > span");

    public SelectedSiteElement(){
        setWebElement(selectedSiteLocator);
    }

}
