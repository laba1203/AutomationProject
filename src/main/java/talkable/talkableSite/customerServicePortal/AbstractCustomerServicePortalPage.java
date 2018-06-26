package talkable.talkableSite.customerServicePortal;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.customerServicePortal.createReferral.PageCreateReferral;
import talkable.talkableSite.customerServicePortal.pendingReferrals.PendingReferralsPage;
import talkable.talkableSite.customerServicePortal.personLookup.PersonLookupPage;
import util.logging.Log;

public class AbstractCustomerServicePortalPage extends AbstractTalkableSitePage{
    private static final By createReferralTabLctr = By.xpath("//a[text()='Create referral']");
    private static final By personLookupTabLctr = By.xpath("//a[text()='Person lookup']");
    private static final By pendingReferralsLctr = By.xpath("//a[text()='Pending referrals']");

    private Element createReferralTab = new Element(createReferralTabLctr, "'Create Referral' tab" );
    private Element personLookupTab = new Element(personLookupTabLctr, "'Person Lookup' tab" );
    private Element pendingReferralsTab = new Element(pendingReferralsLctr, "'Pending Referrals' tab");


    public PageCreateReferral openCreateReferralPage(){
        createReferralTab.click();
        PageCreateReferral page = new PageCreateReferral();
        Log.logRecord("Create Referral page is opened on CSP");
        return page;
    }

    public PersonLookupPage openPersonLookupPage(){
        personLookupTab.click();
        PersonLookupPage page = new PersonLookupPage();
        Log.logRecord("Person Lookup page is opened on CSP");
        return page;
    }

    public PendingReferralsPage openPendingReferralsPage(){
        pendingReferralsTab.click();
        PendingReferralsPage page = new PendingReferralsPage();
        Log.logRecord("Pending Referrals page is opened on CSP");
        return page;
    }

}
