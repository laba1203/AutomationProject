package talkable.talkableSite.reports.performanceOverTime;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.talkableSite.reports.AbstractReportPage;

public class PerformanceOverTimeReportPage extends AbstractReportPage {

    private static final String header = "Performance Over Time";

    public PerformanceOverTimeReportPage(){
        verifyHeader(header);
    }


}
