package customerSite.talkableFrame.commonPages.advocateSignupPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ClosePopupButton extends AbstractElement{
    private By locator = By.cssSelector(".popup-close");

    ClosePopupButton(){
        setWebElement(locator);
    }
}
