package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSiteNameInputErrorMsg extends AbstractElement{

    private static final By locator = By.cssSelector("[name='account[sites_attributes][0][name]'] + div");

    ElmntSiteNameInputErrorMsg(){
        setWebElement(locator);
    }
}
