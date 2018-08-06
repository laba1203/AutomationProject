package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.common.CampaignPlacement;
import util.WaitFactory;
import util.logging.Log;

import java.util.ArrayList;

public class PageCampaignRules extends AbstractCampaignPage{

    private static final By incentiveNamesFromDropDownLctr = By.cssSelector(".Rules-incentives-dropdown a>div:nth-of-type(1)");
    private static final By tagInputLctr = By.xpath("//input[contains(@class, 'tag')]");
    private static final By addTagBtnLctr = By.xpath("//div[contains(@class, 'tag')]/div[@class='btn']");

    private Element redirectOnExpiredClaimCheckbox = new Element(By.xpath("//label/input[@name='redirect_on_expired_claim']/.."), "'RedirectOnExpiredClaim' Checkbox");
    private Element redirectOnExpiredClaimCheckboxValue = new Element(By.xpath("//label/input[@name='redirect_on_expired_claim']"));
    private Element useFacebookAppIdCheckbox = new Element(By.xpath("//label/input[@name='use_facebook_app_id']/.."), "'UseFacebookAppId' checkbox");
    private Element useFacebookAppIdCheckboxValue = new Element(By.xpath("//label/input[@name='use_facebook_app_id']"));
    private Element enablePlainTextVersionCheckbox = new Element(By.xpath("//label/input[@name='enable_plain_text_emails']/.."), "'EnablePlainTextVersion' Checkbox" );
    private Element enablePlainTextVersionCheckboxValue = new Element(By.xpath("//label/input[@name='enable_plain_text_emails']"));

    private ElmntSaveButton saveChangesButton = new ElmntSaveButton();
    private ElmntCampaignNameInput campaignNameInput = new ElmntCampaignNameInput();
    private ElmntCampaignDescriptionInput campaignDescription = new ElmntCampaignDescriptionInput();;
    private ElmntAdvocateOfferDeadlineDate advocateOfferDeadlineDate = new ElmntAdvocateOfferDeadlineDate();
    private ElmntFriendOfferDeadlineDate friendOfferDeadlineDate;
    private ElmntAdOfferDeadlineHours adOfferDeadlineHours;
    private ElmntAdOfferDeadlineMinutes adOfferDeadlineMinutes;
    private ElmntFrOfferDeadlineHours frOfferDeadlineHours;
    private ElmntFrOfferDeadlineMinutes frOfferDeadlineMinutes;

//    Elements initialized only inside the methods:
    public enum DiscountType{ Percentage, FixedAmount}
    public enum CouponCodeType{MultiUse, SingleUse}
    public enum IncentiveType{
        AdvocateSignupIncentive,
        AdvocateSharingIncentive,
        AdvocateReferralIncentive,
        AdvocateNewSubscriber,
        FriendIncentive_NewCustomer,
        FriendIncentive_ExistingCustomer,
        FriendIncentive_ExpiredOffer,
        FriendIncentive_SelfReferral,
        FriendIncentive_NewSubscriber,
        FriendReferredIncentive
    }

    public PageCampaignRules(){

        firstLoading();
        adOfferDeadlineHours = new ElmntAdOfferDeadlineHours();
        adOfferDeadlineMinutes = new ElmntAdOfferDeadlineMinutes();
        if(campaignNavigationMenu.getCampaignPlacement() != CampaignPlacement.Gleam) {
            friendOfferDeadlineDate = new ElmntFriendOfferDeadlineDate();
            frOfferDeadlineHours = new ElmntFrOfferDeadlineHours();
            frOfferDeadlineMinutes = new ElmntFrOfferDeadlineMinutes();
        }
    }

    private PageCampaignRules saveChanges(){
        saveChangesButton.click();
        waitSaving();
        Log.changesAreSaved();

        return new PageCampaignRules();
    }

    public PageCampaignRules setCampaignName(String newName){
        campaignNameInput.clear();
        campaignNameInput.sendKeys(newName);
        return saveChanges();
    }

    public String getCampaignName(){
        campaignNameInput = new ElmntCampaignNameInput();
        return campaignNameInput.getAttribute("value");
    }

    public String getCampaignDescription(){
        return campaignDescription.getText();
    }

    public PageCampaignRules setCampaignDescription(String text){
        campaignDescription.sendKeys(text);
        return saveChanges();
    }

    /*returns time in format "00:00"*/
    public String getAdvocateDeadlineTime(){
        return adOfferDeadlineHours.getSelectedItemText() + ":" + adOfferDeadlineMinutes.getSelectedItemText();
    }

    /*returns time in format "00:00"*/
    public String getFriendDeadlineTime(){
        return frOfferDeadlineHours.getSelectedItemText() + ":" + frOfferDeadlineMinutes.getSelectedItemText();
    }

    public String getAdvocateDeadlineDate(){
        return advocateOfferDeadlineDate.getAttribute("value");
    }

    public String getFriendDeadlineDate(){
        return friendOfferDeadlineDate.getAttribute("value");
    }


    public PageCampaignRules createNewIncentive(IncentiveType incentiveType, int rewardAmount, DiscountType discountType, CouponCodeType couponCodeType){
        // move mouse to element under Create New Incentive button:
        new ElmntCampaignDescriptionInput().moveMouseOver();
        //click Create New Incentive button:
        new ElmntCreateNewIncentiveButton().moveToElementAndClick();
        PopupIncentiveFactory incentivePopup = selectIncentive(incentiveType);
        incentivePopup.createIncentive(rewardAmount, discountType, couponCodeType);

//        return waitLoading();
        waitSaving();
        return new PageCampaignRules();
    }

   /*Note: Format of dates should be MM/DD/YYYY,
   * Hours and Minute format should be equal to values from relevant dropdown lists on UI(Rules page)
   * */
    private PageCampaignRules setDeadlineDates(String advocateOfferDeadlineDate, String adHours, String adMinute, String friendOfferDeadlineDate, String frHour, String frMinute){
        //advocate Deadline Offer values:
        this.advocateOfferDeadlineDate.sendKeys(advocateOfferDeadlineDate);
        adOfferDeadlineHours.searchAndSelect(adHours);
        adOfferDeadlineMinutes.searchAndSelect(adMinute);
        //friend Deadline Offer values:
        this.friendOfferDeadlineDate.sendKeys(friendOfferDeadlineDate);
        frOfferDeadlineHours.searchAndSelect(adHours);
        frOfferDeadlineMinutes.searchAndSelect(adMinute);
        //save changes:
        return saveChanges();
    }

    public PageCampaignRules setDeadlineDates(String advocateOfferDeadlineDate, String adEndTime, String friendOfferDeadlineDate, String frEndTime) {
        return setDeadlineDates(advocateOfferDeadlineDate, adEndTime.substring(0,2), adEndTime.substring(3), friendOfferDeadlineDate, frEndTime.substring(0,2), frEndTime.substring(3));
    }

    public PageCampaignRules addTag(String tag){
        new Element(tagInputLctr, "Tag input field").sendKeys(tag);
        new Element(addTagBtnLctr, "Add tag button").moveToElementAndClick();
        waitSaving();
        return new PageCampaignRules();
    }

    private PopupIncentiveFactory selectIncentive(IncentiveType incentiveType){
        ArrayList<Element> incentiveTypeItems = getElementsList(incentiveNamesFromDropDownLctr);
        Log.debagRecord("size: " + incentiveTypeItems.size());
                String incentiveName = getIncentiveTypeName(incentiveType);
        selectByText(incentiveName, incentiveTypeItems);

        return getIncentivePopup(incentiveType);

    }

    static PopupIncentiveFactory getIncentivePopup(IncentiveType incentiveType){
        if(incentiveType == IncentiveType.AdvocateReferralIncentive || incentiveType == IncentiveType.AdvocateSharingIncentive){
            return new PopupExtendedIncentive();
        }
        else {
            return new PopupIncentive();
        }
    }

    public IncentiveTile getIncentiveTile(IncentiveType incentiveType, String value){
        return new IncentiveGroup(incentiveType).getIncentive(value);
    }

    public PageCampaignRules deleteIncentive(IncentiveType incentiveType, String value){
        IncentiveTile incentive = getIncentiveTile(incentiveType, value);
        incentive.delete();

        return waitLoading();
    }

    private PageCampaignRules waitLoading(){
        new ElmntLoadingMessage();
        firstLoading();
        return new PageCampaignRules();
    }

    private void firstLoading(){
        ElmntLoadingMessage loadingMessage = new ElmntLoadingMessage();
        try {
            wait.until(ExpectedConditions.invisibilityOf(loadingMessage.getWebElement()));
        }catch (TimeoutException e){
            Assert.fail("FAILED: org.openqa.selenium.TimeoutException:");
        }
    }

    public PageCampaignRules switchRedirectOnExpiredClaimCheckbox(){
        redirectOnExpiredClaimCheckbox.click();
        return saveChanges();
    }

    public String getRedirectOnExpiredClaimCheckbox() {
        return redirectOnExpiredClaimCheckboxValue.getAttribute("value");
    }

    public PageCampaignRules switchUseFacebookAppIdCheckbox(){
        useFacebookAppIdCheckbox.moveToElementAndClick();
        return saveChanges();
    }

    public String getUseFacebookAppIdCheckbox() {
        return useFacebookAppIdCheckboxValue.getAttribute("value");
    }

    public PageCampaignRules switchPlainTextVersionCheckbox(){
        enablePlainTextVersionCheckbox.moveToElementAndClick();
        return saveChanges();
    }

    public String getPlainTextVersionCheckbox(){
        return enablePlainTextVersionCheckboxValue.getAttribute("value");
    }

    static String getIncentiveTypeName(IncentiveType incentiveType){

        String name;
        switch (incentiveType){
            case AdvocateSignupIncentive:
                name = "Advocate Signup Incentive";
                break;
            case AdvocateSharingIncentive:
                name = "Advocate Sharing Incentive";
                break;
            case AdvocateReferralIncentive:
                name = "Advocate Referral Incentive";
                break;
            case AdvocateNewSubscriber:
                name = "Advocate Referral Incentive (New Subscriber)";
                break;
            case FriendReferredIncentive:
                name = "Friend Referred Incentive";
                break;
            case FriendIncentive_NewCustomer:
                name = "Friend Incentive (New Customer)";
                break;
            case FriendIncentive_ExpiredOffer:
                name = "Friend Incentive (Expired Offer)";
                break;
            case FriendIncentive_ExistingCustomer:
                name = "Friend Incentive (Existing Customer)";
                break;
            case FriendIncentive_SelfReferral:
                name = "Friend Incentive (Self-Referral)";
                break;
            case FriendIncentive_NewSubscriber:
                name = "Friend Incentive (New Subscriber)";
                break;

            default:
                name = null;
                Assert.fail("Incorrect incentiveType: " + incentiveType);
                break;
        }
        return name;
    }


}
