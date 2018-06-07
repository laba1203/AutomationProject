package talkable.talkableSite.reports.rewards;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;

public class RewardsReportPage extends AbstractTalkableSitePage{

    private static final By totalLctr = By.xpath("//span[@class = 'base-form-label']");

    public String getTotalRowsCount(){
        return new Element(totalLctr).getText();
    }
}
