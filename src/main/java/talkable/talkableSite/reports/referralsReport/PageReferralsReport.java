package talkable.talkableSite.reports.referralsReport;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.customerService.createReferral.PageCreateReferral;

public class PageReferralsReport extends AbstractTalkableSitePage{

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
}
