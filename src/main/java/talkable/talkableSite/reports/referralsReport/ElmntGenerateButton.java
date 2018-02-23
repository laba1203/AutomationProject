package talkable.talkableSite.reports.referralsReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntGenerateButton extends AbstractElement {

    private By locator = By.cssSelector("input[value='Generate']");

    public ElmntGenerateButton(){
        setWebElement(locator);
    }

}
