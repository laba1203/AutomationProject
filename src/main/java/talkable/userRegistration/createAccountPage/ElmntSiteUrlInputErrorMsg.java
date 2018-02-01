package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSiteUrlInputErrorMsg extends AbstractElement{

    private static final By locator = By.cssSelector("[name='account[sites_attributes][0][url]'] + div");

    ElmntSiteUrlInputErrorMsg(){
        setWebElement(locator);
    }
}
