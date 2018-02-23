package talkable.talkableSite.reports.referralsReport;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.customerService.createReferral.PageCreateReferral;
import talkable.talkableSite.headerFrame.Header;

public class PageReferralsReport extends AbstractElementsContainer{

    public Header header;
    private ElmntCreateReferralManual createReferralManual;

    public PageReferralsReport(){

        header = new Header();
        createReferralManual = new ElmntCreateReferralManual();



    }

    public PageCreateReferral clickCreateReferral(){
        createReferralManual.click();
        return new PageCreateReferral();

    }
}
