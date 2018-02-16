package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntMultiUseCouponCodeInput extends AbstractElement{
    private static final  By locator = By.cssSelector("input[name='multi_use_coupon_code']");

    ElmntMultiUseCouponCodeInput(){
        setWebElement(locator);
    }
}
