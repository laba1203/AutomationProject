package talkable.loginPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class PasswordInput extends AbstractElement {

    private By passwordFieldLocator = By.name("password");

    public PasswordInput(){
        setWebElement(passwordFieldLocator);
    }



}
