package talkable.talkableSite.reports.couponLists;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import util.TestArtifactsProvider;

public class NewCouponListPage extends AddCouponsPage{

    private static final By nameLctr = By.xpath("//input[@name='coupon_list[name]']");
    private static final By expirationDateLctr = By.xpath("//input[@name='coupon_list[expires_at]']");
    private static final By amountInputLctr = By.xpath("//input[@name='coupon_list[amount]']");
    private static final By percentageChekboxLctr = By.xpath("//input[@id='coupon_list_percentage_discount_true']");
    private static final By amountChekboxLctr = By.xpath("//input[@id='coupon_list_percentage_discount_false']");
//    private static final By uploadFileInputLctr = By.xpath("//input[@name='coupon_list[upload_file]']");
//    private static final By createNewCounListBtnLctr = By.xpath("//input[@name='commit']");
//    private static final By manualCouponListFieldLctr = By.xpath("//*[@name='coupon_list[coupons_codes]']");

    private Element nameInput = new Element(nameLctr, "'Coupon Code List Name' field");
    private Element expirationDateInput = new Element(expirationDateLctr, "'Expiration Date' field");
    private Element amountInput = new Element(amountInputLctr, "'Amount' field");
    private Element percentageCheckbox = new Element(percentageChekboxLctr, "'Percentage' checkbox");
    private Element amountCheckbox = new Element(amountChekboxLctr, "'Amount' checkbox");
//    private Element uploadFileInput = new Element(uploadFileInputLctr, "Upload file input");
//    private Element createNewCouponListBtn = new Element(createNewCounListBtnLctr, "'Create New Coupon List' button");
//    private Element manualCouponListField = new Element(manualCouponListFieldLctr, "Manual Coupon Codes field");


    public NewCouponListPage populateFields(String listName, String expirationDate, int amount){
        nameInput.sendKeys(listName);
        expirationDateInput.sendKeys(expirationDate);
        amountInput.sendKeys(String.valueOf(amount));
        return new NewCouponListPage();
    }

    public NewCouponListPage selectDiscountType(PageCampaignRules.DiscountType discountType){
        switch (discountType){
            case Percentage:
                percentageCheckbox.click();
                break;
            case FixedAmount:
                amountCheckbox.click();
                break;
        }
        return new NewCouponListPage();
    }

}
