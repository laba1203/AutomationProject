package talkable.talkableSite.reports.rewards;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.reports.AbstractReportPage;

public class RewardsReportPage extends AbstractReportPage{

    private static final String header = "Rewards";
    private static final By totalLctr = By.xpath("//span[@class = 'base-form-label']");


    public RewardsReportPage(){
        verifyHeader(header);
    }

    public String getTotalRowsCount(){
        return new Element(totalLctr).getText();
    }
}
