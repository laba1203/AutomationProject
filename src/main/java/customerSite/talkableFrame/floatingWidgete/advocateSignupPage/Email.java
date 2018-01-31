package customerSite.talkableFrame.floatingWidgete.advocateSignupPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class Email extends AbstractElement{
    private By locator = By.cssSelector("input[name='affiliate_member[email]']");

    Email(){
        setWebElement(locator);
    }
}
