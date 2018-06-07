package talkable.talkableSite.reports.referralsReport;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.customerServicePortal.createReferral.PageCreateReferral;

public class PageReferralsReport extends AbstractTalkableSitePage{

    private static final By totalLctr = By.xpath("//*[@class='entries_info']");

    public PageReferralsReport(){
        setVisibleElements();
    }

    private void setVisibleElements(){
        new ElmntCreateReferralManual();
    }

    public PageCreateReferral clickCreateReferral(){
        new ElmntCreateReferralManual().click();
        return new PageCreateReferral();
    }

    public FirstRow getFirstReferralRow(){
        return new FirstRow();
    }

    public String getTotalRowsCount(){
        return new Element(totalLctr).getText();
    }
}
