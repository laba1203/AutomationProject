package talkable.loginPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntLoginButton extends AbstractElement{

    private static final By loginButtonLocator = By.cssSelector(".ac-login-button");

    ElmntLoginButton(){
        setWebElement(loginButtonLocator);
    }
}
