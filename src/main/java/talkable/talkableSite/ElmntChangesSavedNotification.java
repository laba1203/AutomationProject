package talkable.talkableSite;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntChangesSavedNotification extends AbstractElement{
    private static final By locator = By.xpath("//div[text()='Changes have been saved']");

    ElmntChangesSavedNotification(){
        setWebElement(locator);
    }
}
