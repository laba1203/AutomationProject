package talkable.talkableSite.customerServicePortal.personLookup;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class PersonWasAnonymizedMsg extends AbstractElement{
    private static final By locator = By.xpath("//*[text()='Person was successfully anonymized']");

    PersonWasAnonymizedMsg(){
        setWebElement(locator);
    }

}
