package talkable.common.elements;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

public class RangeDatePicker extends AbstractElementsContainer{
    private static final By okBtnLctr = By.xpath("//button[contains(@class, 'rangepicker-btn-success')]");

    public void clickOk(){
        new Element(okBtnLctr, "OK in Range date picker")
                .click();
    }
}
