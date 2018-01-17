package talkable.loginPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class EmailInput extends AbstractElement {
    private static  final By emailFieldLocator = By.cssSelector(".base-input.ac-user-email");

    public EmailInput(){
        setWebElement(emailFieldLocator);
    }

}
