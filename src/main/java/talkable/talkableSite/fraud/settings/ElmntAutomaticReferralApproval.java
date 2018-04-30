package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAutomaticReferralApproval extends AbstractElement{
    private static final By locator = By.xpath("//label[@for = 'referrals_approval_1']");

    ElmntAutomaticReferralApproval(){
        setWebElement(locator);
    }
}
