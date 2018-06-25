package common.cases.functionalities;

import common.cases.CommonScenarios;
import org.testng.Assert;
import talkable.talkableSite.customerServicePortal.AbstractCustomerServicePortalPage;
import talkable.talkableSite.customerServicePortal.createReferral.PageCreateReferral;
import talkable.talkableSite.customerServicePortal.personLookup.PersonInfoSection;
import talkable.talkableSite.customerServicePortal.personLookup.PersonLookupPage;
import talkable.talkableSite.customerServicePortal.personLookup.ReferralDetailsSection;
import util.logging.Log;

public class CspScenarios extends CommonScenarios{

    public static PageCreateReferral openCreateReferralPage(){
        return new AbstractCustomerServicePortalPage().openCreateReferralPage();
    }

    public static PersonLookupPage openPersonLookupPage(){
        return new AbstractCustomerServicePortalPage().openPersonLookupPage();
    }

    /**
     * Method to open referral page and create new referral.
     */
    public static void createNewReferral(String advocateEmail, int orderSubtotal, String campaignName){
        openCreateReferralPage()
                .createReferralNew(advocateEmail, orderSubtotal, campaignName);
        Log.logRecord("New Referral created for advocate: <" + advocateEmail + ">.");
    }

    public static void searchPersonLookup(String email){
        openPersonLookupPage()
                .searchPerson(email);
        Log.logRecord("Person with email <" + email + "> is found in Person Lookup.");
    }

    /*Person info should be already displayed in the Person Lookup
    */
    public static void openReferralDetailsFromPersonLookup(String friendEmail){
        ReferralDetailsSection referralDetails = new PersonLookupPage()
                .getPersonInfoSection()
                .openReferralDetailsFor(friendEmail);
        Assert.assertEquals(
                referralDetails.getFriendEmail(),
                friendEmail,
                "FAILED: Incorrect Friend email is displayed in the Referral Details on Person lookup. For advocate <"
                        + new PersonLookupPage().getPersonInfoSection().getUserEmail() + ">.");
        Log.logRecord("Referral Details tab is opened on Person Lookup page.");
    }

    public static String getAdvocateRewardStatusFromReferralDetails(){
        return new ReferralDetailsSection().getAdvocateRewardStatus();
    }

    public static String getFriendRewardStatusFromReferralDetails(){
        return new ReferralDetailsSection().getFriendRewardStatus();
    }

    public static String getPurchasesRowsCountFromPersonLookup(){
        return new PersonLookupPage().getPersonInfoSection().getPurchasesRowsCount();
    }

//    public static void waitTillPurchasesRowsCountChanges(String initialCount){
//        new PersonInfoSection().waitTillPurchaseCountChanged(initialCount);
//    }

    public static void openPurchasesTabOnPersonLookupPage(){
        new PersonInfoSection().openPurchasesTab();
    }

    public static void openReferralsTabOnPersonLookupPage(){

    }


}
