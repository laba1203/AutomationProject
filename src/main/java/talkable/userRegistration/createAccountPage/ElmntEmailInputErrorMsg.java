package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntEmailInputErrorMsg extends AbstractElement{

    private static final By locator = By.cssSelector("[name='account[users_attributes][0][email]'] + div");

    ElmntEmailInputErrorMsg(){
        setWebElement(locator);
    }
}
