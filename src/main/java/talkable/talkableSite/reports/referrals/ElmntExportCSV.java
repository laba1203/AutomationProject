package talkable.talkableSite.reports.referrals;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntExportCSV extends AbstractElement {

    private static final By locator = By.cssSelector("input[value='Export CSV']");

    public ElmntExportCSV() {
        setWebElement(locator);

    }
}