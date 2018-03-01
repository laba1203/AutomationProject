package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntClosePopupButton extends AbstractElement{
    private static final By locator = By.cssSelector(".base-modal-close");

    ElmntClosePopupButton(){
        setWebElement(locator);
    }
}
