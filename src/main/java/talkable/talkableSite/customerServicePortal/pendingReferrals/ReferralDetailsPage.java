package talkable.talkableSite.customerServicePortal.pendingReferrals;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.customerServicePortal.AbstractCustomerServicePortalPage;

public class ReferralDetailsPage extends AbstractCustomerServicePortalPage{

    private static final By referralStatusLctr = By.xpath("//*[contains(@class, 'status-text')]");

    public String getReferralStatus(){
        return new Element(referralStatusLctr).getText().substring(17);
    }
}
