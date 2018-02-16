package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElementsContainer;
import org.testng.Assert;

class PopupIncentive extends AbstractElementsContainer implements PopupIncentiveFactory{

    ElmntCreateIncentiveButton createIncentiveButton;
    private ElmntRewardAmountInput rewardAmountInput;
    private ElmntPercentageDiscountTypeRadiobutton percentageDiscountTypeRadiobutton;
    private ElmntFixedAmountDiscountTypeRadiobutton fixedAmountDiscountTypeRadiobutton;
    private ElmntMultiUseRadiobutton multiUseRadiobutton;
    private ElmntSingleUseRadiobutton singleUseRadiobutton;
    private ElmntMultiUseCouponCodeInput multiUseCouponCodeInput;


    void setDiscountType(PageCampaignRules.DiscountType discountType){
        percentageDiscountTypeRadiobutton = new ElmntPercentageDiscountTypeRadiobutton();
        fixedAmountDiscountTypeRadiobutton = new ElmntFixedAmountDiscountTypeRadiobutton();
        switch (discountType){
            case Percentage:
                percentageDiscountTypeRadiobutton.click();
                break;
            case FixedAmount:
                fixedAmountDiscountTypeRadiobutton.click();
                break;
            default:
                Assert.fail("Incorrect discountType: " + discountType.toString());
                break;
        }
    }

    void setRewardAmount(int amount){
        rewardAmountInput = new ElmntRewardAmountInput();
        rewardAmountInput.sendKeys(String.valueOf(amount));
    }

    void setCouponCodeType(PageCampaignRules.CouponCodeType couponCodeType){
        multiUseRadiobutton = new ElmntMultiUseRadiobutton();
        singleUseRadiobutton = new ElmntSingleUseRadiobutton();
        switch (couponCodeType){
            case MultiUse:
                multiUseRadiobutton.clear();
                multiUseCouponCodeInput = new ElmntMultiUseCouponCodeInput();
                multiUseCouponCodeInput.sendKeys("DEFAULT_COUPON_CODE");
                break;
            case SingleUse:
                singleUseRadiobutton.click();
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
