package talkable.reports.reportsPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class PeopleButton extends AbstractElement{

    private static final By locator = By.cssSelector("a[href$='people']");

    public PeopleButton(){
        setWebElement(locator);
    }
}
