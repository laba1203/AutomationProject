package talkable.talkableSite.reports.reportsPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class PreviousCustomersButton extends AbstractElement{

    private static final By locator = By.cssSelector("a[href$='previous_customers']");

    public PreviousCustomersButton(){
        setWebElement(locator);
    }
}
