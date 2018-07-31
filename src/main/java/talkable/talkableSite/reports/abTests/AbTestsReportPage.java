package talkable.talkableSite.reports.abTests;

import org.openqa.selenium.By;
import talkable.talkableSite.reports.AbstractReportPage;

public class AbTestsReportPage extends AbstractReportPage {
    private static final By headerLctr = By.xpath("//div[contains(@class, 'headline')]/h1[1]");
    private static final String header = "A/B Tests";

    public AbTestsReportPage(){
        verifyHeader(headerLctr, header);
    }


}
