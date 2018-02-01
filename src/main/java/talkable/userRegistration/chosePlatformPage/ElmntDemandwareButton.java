package talkable.userRegistration.chosePlatformPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntDemandwareButton extends AbstractElement{

    private static final By locator = By.cssSelector("[data-platform='demandware']");

    ElmntDemandwareButton(){
        setWebElement(locator);
    }
}
