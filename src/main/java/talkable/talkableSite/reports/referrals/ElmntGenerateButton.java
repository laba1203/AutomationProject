package talkable.talkableSite.reports.referrals;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntGenerateButton extends AbstractElement {

    private static final By locator = By.cssSelector("input[value='Generate']");

    ElmntGenerateButton(){
        setWebElement(locator);
    }

}
