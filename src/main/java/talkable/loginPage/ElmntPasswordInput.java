package talkable.loginPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntPasswordInput extends AbstractElement {

    private static final By passwordFieldLocator = By.name("password");

    ElmntPasswordInput(){
        setWebElement(passwordFieldLocator);
    }



}
