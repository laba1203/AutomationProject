package customerSite.talkableFrame.commonPages.advocateSignupPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class InviteFriendsButton extends AbstractElement{
    private By locator = By.cssSelector("input[name='commit']");

    InviteFriendsButton(){
        setWebElement(locator);
    }
}
