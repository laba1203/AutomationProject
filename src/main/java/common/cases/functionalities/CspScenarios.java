package common.cases.functionalities;

import common.cases.CommonScenarios;
import talkable.talkableSite.customerServicePortal.AbstractCustomerServicePortalPage;
import talkable.talkableSite.customerServicePortal.createReferral.PageCreateReferral;
import util.logging.Log;

public class CspScenarios extends CommonScenarios{

    public static PageCreateReferral openCreateReferralPage(){
        return new AbstractCustomerServicePortalPage().openCreateReferralPage();
    }

    /**
     * Method to open referral page and create new referral.
     */
    public static void createNewReferral(String advocateEmail, int orderSubtotal, String campaignName){
        openCreateReferralPage()
                .createReferralNew(advocateEmail, orderSubtotal, campaignName);
        Log.logRecord("New Referral created for advocate: <" + advocateEmail + ">.");
    }
}
