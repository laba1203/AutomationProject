package talkable.talkableSite.reports.referrals;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateReferralManual extends AbstractElement {

    private static final By locator = By.linkText("Create Referral Manually");

    ElmntCreateReferralManual(){
        setWebElement(locator);
    }

    }

