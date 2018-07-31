package talkable.talkableSite.reports.referrals;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.customerServicePortal.createReferral.PageCreateReferral;
import talkable.talkableSite.reports.AbstractReportPage;

public class PageReferralsReport extends AbstractReportPage{


    private static final String header = "Referrals";
    private static final By totalLctr = By.xpath("//*[@class='entries_info']");

    public PageReferralsReport(){
        verifyHeader(header);
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
