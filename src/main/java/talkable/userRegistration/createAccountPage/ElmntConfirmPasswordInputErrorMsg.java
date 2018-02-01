package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntConfirmPasswordInputErrorMsg extends AbstractElement{

    private static final By locator = By.cssSelector("[name='account[users_attributes][0][password_confirmation]'] + div");

    ElmntConfirmPasswordInputErrorMsg(){
        setWebElement(locator);
    }
}
