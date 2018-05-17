package talkable.talkableSite.fraud.settings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntReferralApprovalDelayInput extends AbstractElement{
    private static final By locator = By.xpath("//*[@name='referralsApprovalDelay']");

    ElmntReferralApprovalDelayInput(){
        setWebElement(locator);
    }

}
