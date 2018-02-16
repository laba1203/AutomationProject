package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCouponCodeRewardTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.cssSelector("input[value = 'discount_coupon']");

    ElmntCouponCodeRewardTypeRadiobutton(){
        setWebElement(locator);
    }
}
