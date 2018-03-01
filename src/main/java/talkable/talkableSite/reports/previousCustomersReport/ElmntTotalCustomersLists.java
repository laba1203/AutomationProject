package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntTotalCustomersLists extends AbstractElement{

    private static By locator = By.cssSelector(".mbxxl>.App-layout-content>span");

    ElmntTotalCustomersLists(){
        setWebElement(locator);
    }
}
