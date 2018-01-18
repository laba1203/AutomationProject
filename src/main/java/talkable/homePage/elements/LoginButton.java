package talkable.homePage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class LoginButton extends AbstractElement{
    private static final By locator = By.className("js-ga-click");

    public LoginButton(){
        setWebElement(locator);
    }
}
