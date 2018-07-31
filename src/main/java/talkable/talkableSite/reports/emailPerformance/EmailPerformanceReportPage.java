package talkable.talkableSite.reports.emailPerformance;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.talkableSite.reports.AbstractReportPage;

public class EmailPerformanceReportPage extends AbstractReportPage {
    private static final String header = "Email Performance";

    public EmailPerformanceReportPage(){
        verifyHeader(header);
    }

}
