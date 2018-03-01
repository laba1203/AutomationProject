package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntUploadNewCSVButton extends AbstractElement{
    private static final By locator = By.linkText("Upload new CSV");

    ElmntUploadNewCSVButton(){
        setWebElement(locator);
    }


}
