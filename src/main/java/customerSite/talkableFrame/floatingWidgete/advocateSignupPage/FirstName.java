package customerSite.talkableFrame.floatingWidgete.advocateSignupPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class FirstName extends AbstractElement{
    private By locator = By.cssSelector("input[name='affiliate_member[first_name]']");

    FirstName(){
        setWebElement(locator);
    }
}
