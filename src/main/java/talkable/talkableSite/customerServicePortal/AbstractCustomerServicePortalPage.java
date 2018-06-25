package talkable.talkableSite.customerServicePortal;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.customerServicePortal.createReferral.PageCreateReferral;
import talkable.talkableSite.customerServicePortal.personLookup.PersonLookupPage;
import util.logging.Log;

public class AbstractCustomerServicePortalPage extends AbstractTalkableSitePage{
    private static final By createReferralTabLctr = By.xpath("//a[text()='Create Referral']");
    private static final By personLookupTabLctr = By.xpath("//a[text()='Person lookup']");

    private Element createReferralTab = new Element(createReferralTabLctr, "'Create Referral' tab" );
    private Element personLookupTab = new Element(personLookupTabLctr, "'Person Lookup' tab" );


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

}
