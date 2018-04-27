package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntManualReferralApproval extends AbstractElement{
    private static final By locator = By.xpath("//label[@for = 'referrals_approval_0']");

    ElmntManualReferralApproval(){
        setWebElement(locator);
    }
}
