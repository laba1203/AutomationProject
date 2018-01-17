package talkable.loginPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class LoginButton extends AbstractElement{

    private static final By loginButtonLocator = By.cssSelector(".ac-login-button");

    public LoginButton(){
        setWebElement(loginButtonLocator);
    }
}
