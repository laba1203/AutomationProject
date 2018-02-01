package talkable.userRegistration.chosePlatformPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntOtherButton extends AbstractElement{

    private static final By locator = By.cssSelector("[data-platform='custom']");

    ElmntOtherButton(){
        setWebElement(locator);
    }
}
