package talkable.talkableSite.reports.newAffiliateMember;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntEmailInput extends AbstractElement{
    private static final By locator = By.cssSelector("[name='origin[email]']");

    ElmntEmailInput(){
        setWebElement(locator);
    }
}
