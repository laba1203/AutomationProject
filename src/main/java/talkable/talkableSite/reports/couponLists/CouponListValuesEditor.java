package talkable.talkableSite.reports.couponLists;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;

interface CouponListValuesEditor {
    By nameLctr = By.xpath("//input[@name='coupon_list[name]']");
    By expirationDateLctr = By.xpath("//input[@name='coupon_list[expires_at]']");
    By amountInputLctr = By.xpath("//input[@name='coupon_list[amount]']");
    By percentageChekboxLctr = By.xpath("//input[@id='coupon_list_percentage_discount_true']");
    By amountChekboxLctr = By.xpath("//input[@id='coupon_list_percentage_discount_false']");


    default NewCouponListPage populateFields(String listName, String expirationDate, int amount){
        new Element(nameLctr, "'Coupon Code List Name' field").clearAndSendKeys(listName);
        new Element(expirationDateLctr, "'Expiration Date' field").clearAndSendKeys(expirationDate);
        new Element(amountInputLctr, "'Amount' field").clearAndSendKeys(String.valueOf(amount));
        return new NewCouponListPage();
    }

    default NewCouponListPage populateFields(String listName, int amount){
        new Element(nameLctr, "'Coupon Code List Name' field").clearAndSendKeys(listName);
        new Element(amountInputLctr, "'Amount' field").clearAndSendKeys(String.valueOf(amount));
        return new NewCouponListPage();
    }

    default NewCouponListPage selectDiscountType(PageCampaignRules.DiscountType discountType){
        switch (discountType){
            case Percentage:
                new Element(percentageChekboxLctr, "'Percentage' checkbox").click();
                break;
            case FixedAmount:
                new Element(amountChekboxLctr, "'Amount' checkbox").click();
                break;
        }
        return new NewCouponListPage();
    }
}
