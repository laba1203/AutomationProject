package talkable.reports.previousCustomersReport.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ClosePopupButton extends AbstractElement{
    private static final By locator = By.cssSelector(".base-modal-close");

    public ClosePopupButton(){
        setWebElement(locator);
    }
}
