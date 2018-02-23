package talkable.reports.referralsReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntExportCSV extends AbstractElement {

    private By locator = By.cssSelector("input[value='Export CSV']");

    public ElmntExportCSV() {
        setWebElement(locator);

    }
}