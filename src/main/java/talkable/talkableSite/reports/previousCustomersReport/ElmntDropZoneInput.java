package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import util.logging.Log;

class ElmntDropZoneInput extends AbstractElement{

    private static final By locator = By.cssSelector("body>input");//("input[name='previous_customer_upload[object_key]']");

    ElmntDropZoneInput(){
        setWebElement(locator);
    }

}
