package talkable.reports.referralsReport;

import abstractObjects.AbstractElement;
import abstractObjects.AbstractElementsContainer;
import talkable.customerService.createReferral.PageCreateReferral;
import talkable.headerFrame.Header;

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
