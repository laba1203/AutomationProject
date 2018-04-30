package talkable.talkableSite.reports.referralsReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntGenerateButton extends AbstractElement {

    private static final By locator = By.cssSelector("input[value='Generate']");

    ElmntGenerateButton(){
        setWebElement(locator);
    }

}
