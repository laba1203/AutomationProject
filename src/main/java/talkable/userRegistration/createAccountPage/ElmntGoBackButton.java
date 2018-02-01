package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntGoBackButton extends AbstractElement{

    private static final By locator = By.linkText("Go back");

    ElmntGoBackButton(){
        setWebElement(locator);
    }
}
