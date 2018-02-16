package talkable.campaign.pages.campaignRulesPage;

public interface PopupIncentiveFactory {
    PageCampaignRules createIncentive(int amount, PageCampaignRules.DiscountType discountType, PageCampaignRules.CouponCodeType couponCodeType);
}
