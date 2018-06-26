package talkable.talkableSite.campaign.pages.campaignRulesPage;

/*Class for popup incentive which currently used in
* Advocate Referral Incentive popup
* Advocate Signup Incentive popup*/
class PopupExtendedIncentive extends PopupIncentive implements PopupIncentiveFactory{
    private ElmntCouponCodeRewardTypeRadiobutton couponCodeRadiobutton;
    private ElmntRebateRewardTypeRadiobutton rebateRadiobutton;
    private ElmntNonMonetaryRewardTypeRadiobutton nonMonetaryRadiobutton;

    PopupExtendedIncentive(){
        couponCodeRadiobutton = new ElmntCouponCodeRewardTypeRadiobutton();
        rebateRadiobutton = new ElmntRebateRewardTypeRadiobutton();
        nonMonetaryRadiobutton = new ElmntNonMonetaryRewardTypeRadiobutton();
    }

    @Override
    public PageCampaignRules createIncentive(int rewardAmount, PageCampaignRules.DiscountType discountType, PageCampaignRules.CouponCodeType couponCodeType){
        couponCodeRadiobutton.click();
        setRewardAmount(rewardAmount);
        setDiscountType(discountType);
        setCouponCodeType(couponCodeType); //default coupon code will be selected
        createIncentiveButton.click();

        return new PageCampaignRules();
    }


}
