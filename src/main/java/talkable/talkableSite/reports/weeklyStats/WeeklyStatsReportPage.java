package talkable.talkableSite.reports.weeklyStats;

import abstractObjects.Element;
import org.openqa.selenium.By;

import org.testng.Assert;
import talkable.talkableSite.reports.AbstractReportPage;

public class WeeklyStatsReportPage extends AbstractReportPage {
    private static final By headerLctr = By.xpath("//h1[1]");
    private static final String header = "Weekly Stats";

    public WeeklyStatsReportPage(){
        verifyHeader(headerLctr, header);
    }

}
