package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntDropZoneInput extends AbstractElement{

    private static final By locator = By.cssSelector("body>input");//("input[name='previous_customer_upload[object_key]']");

    ElmntDropZoneInput(){
        setWebElement(locator);
    }

    //todo: remove this elements when common element will be tested (in Image Editor --> Upload)

}
