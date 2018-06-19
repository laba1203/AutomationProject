package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.testng.Assert;

class PopupIncentive extends AbstractElementsContainer implements PopupIncentiveFactory{

    Element createIncentiveButton = new Element(By.xpath("//button[text() = 'Create Incentive']"), "'Create Incentive' button");


    void setDiscountType(PageCampaignRules.DiscountType discountType){
        switch (discountType){
            case Percentage:
                new ElmntPercentageDiscountTypeRadiobutton().click();
                break;
            case FixedAmount:
                new ElmntFixedAmountDiscountTypeRadiobutton().click();
                break;
            default:
                Assert.fail("Incorrect discountType: " + discountType.toString());
                break;
        }
    }

    void setRewardAmount(int amount){
        new ElmntRewardAmountInput().sendKeys(String.valueOf(amount));
    }

    void setCouponCodeType(PageCampaignRules.CouponCodeType couponCodeType){
        switch (couponCodeType){
            case MultiUse:
                new ElmntMultiUseRadiobutton().click();
                new ElmntMultiUseCouponCodeInput().sendKeys("DEFAULT_COUPON_CODE");
                break;
            case SingleUse:
                new ElmntSingleUseRadiobutton().click();
                break;
            //should be extended with selection of coupon lists
            default:
                Assert.fail("Incorrect couponCodeType: " + couponCodeType.toString());
                break;
        }

    }

    public PageCampaignRules createIncentive(int amount, PageCampaignRules.DiscountType discountType, PageCampaignRules.CouponCodeType couponCodeType){
        setRewardAmount(amount);
        setDiscountType(discountType);
        setCouponCodeType(couponCodeType);
        createIncentiveButton.click();

        return new PageCampaignRules();
    }






}
