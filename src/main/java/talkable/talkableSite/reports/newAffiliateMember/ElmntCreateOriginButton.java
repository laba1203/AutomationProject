package talkable.talkableSite.reports.newAffiliateMember;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateOriginButton extends AbstractElement{
    private static final By locator = By.cssSelector("[value='Create Origin']");

    ElmntCreateOriginButton(){
        setWebElement(locator);
    }
}
