package talkable.talkableSite.headerFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ReportsButton extends AbstractElement {
    private static  final By reportsButtonLocator = By.linkText("Reports");

    public ReportsButton(){
        setWebElement(reportsButtonLocator);
    }
}
