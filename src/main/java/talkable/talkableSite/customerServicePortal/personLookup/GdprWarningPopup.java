package talkable.talkableSite.customerServicePortal.personLookup;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

public class GdprWarningPopup extends AbstractElementsContainer{
    private static final By okBtnLctr = By.xpath("//div[contains(@class, 'shown')]//div[contains(text(),'Ok')]");

    private Element okBtn = new Element(okBtnLctr, "OK button");

    void clickOk(){
        okBtn.click();
    }
}
