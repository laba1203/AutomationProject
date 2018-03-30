package talkable.homePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntLoginButton extends AbstractElement{
    private static final By locator = By.className("js-ga-click");

    ElmntLoginButton(){
        setWebElement(locator);
    }
}
