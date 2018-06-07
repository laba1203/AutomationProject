package talkable.talkableSite.customerServicePortal;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.customerServicePortal.createReferral.PageCreateReferral;
import util.logging.Log;

public class AbstractCustomerServicePortalPage extends AbstractTalkableSitePage{
    private static final Element createReferralTab = new Element(By.xpath("//a[text()='Create Referral']"), "'Create Referral' tab" );

    public PageCreateReferral openCreateReferralPage(){
        createReferralTab.click();
        PageCreateReferral page = new PageCreateReferral();
        Log.logRecord("Create Referral page is opened on CSP");
        return page;
    }
}
