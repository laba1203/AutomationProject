package talkable.talkableSite.reports.referralsReport;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntCreateReferralManual extends AbstractElement {

    private By locator = By.linkText("Create Referral Manually");

    public ElmntCreateReferralManual(){ setWebElement(locator);}

    }

