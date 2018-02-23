package talkable.talkableSite.reports.previousCustomersReport.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class TotalCustomersLists extends AbstractElement{

    private static By locator = By.cssSelector(".mbxxl>.App-layout-content>span");

    public TotalCustomersLists(){
        setWebElement(locator);
    }
}
