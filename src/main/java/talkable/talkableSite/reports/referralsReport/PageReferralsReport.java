package talkable.talkableSite.reports.referralsReport;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.customerService.createReferral.PageCreateReferral;

public class PageReferralsReport extends AbstractTalkableSitePage{

//    public Header header;
    private ElmntCreateReferralManual createReferralManual;

    public PageReferralsReport(){

//        header = new Header();
        createReferralManual = new ElmntCreateReferralManual();



    }

    public PageCreateReferral clickCreateReferral(){
        createReferralManual.click();
        return new PageCreateReferral();

    }
}
