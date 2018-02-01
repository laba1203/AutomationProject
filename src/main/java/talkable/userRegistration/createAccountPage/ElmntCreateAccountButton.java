package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateAccountButton extends AbstractElement{

    private static final By locator = By.cssSelector("[name='commit']");

    ElmntCreateAccountButton(){
        setWebElement(locator);
    }
}
