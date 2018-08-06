package customerSite.talkableFrame.commonPages.advocateSignupPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class FirstName extends AbstractElement{
    private static final By locator = By.xpath("//input[@name='affiliate_member[first_name]']");

    FirstName(){
        setWebElement(locator);
    }
}
