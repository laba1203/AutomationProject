package talkable.reports.reportsPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ReferralsButton extends AbstractElement{

    private static final By locator = By.cssSelector("a[href$='referrals']");

    public ReferralsButton(){
        setWebElement(locator);
    }
}
