package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCouponCodeRewardTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.cssSelector("label[for = 'discount_coupon']");

    ElmntCouponCodeRewardTypeRadiobutton(){
        setWebElement(locator);
    }
}
