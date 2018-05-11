package talkable.loginPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntEmailInput extends AbstractElement {
    private static final By emailFieldLocator = By.cssSelector(".base-input.ac-user-email");

    ElmntEmailInput(){
        setWebElement(emailFieldLocator);
    }
}
