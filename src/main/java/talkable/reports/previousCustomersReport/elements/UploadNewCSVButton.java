package talkable.reports.previousCustomersReport.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class UploadNewCSVButton extends AbstractElement{
    private static final By locator = By.linkText("Upload new CSV");

    public UploadNewCSVButton(){
        setWebElement(locator);
    }


}
