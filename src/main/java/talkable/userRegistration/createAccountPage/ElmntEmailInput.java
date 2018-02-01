package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntEmailInput extends AbstractElement{

    private static final By locator = By.cssSelector("[name='account[users_attributes][0][email]']");

    ElmntEmailInput(){
        setWebElement(locator);
    }
}
