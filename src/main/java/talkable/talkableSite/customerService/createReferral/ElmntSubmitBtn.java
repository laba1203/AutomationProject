package talkable.talkableSite.customerService.createReferral;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntSubmitBtn extends AbstractElement{
    private By locator = By.cssSelector("input[value='Create Referral']");
    public ElmntSubmitBtn(){setWebElement(locator);}
}
