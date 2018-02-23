package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.DrivenElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;
import util.logging.Log;

import java.util.ArrayList;

public class PageCampaignRules extends AbstractCampaignPage{

//    public CampaignNavigationMenu navigationMenu;
    private ElmntSaveButton saveChangesButton;
    private ElmntCampaignNameInput campaignNameInput;
    private ElmntCampaignDescriptionInput campaignDescription;
    private ElmntAdvocateOfferDeadlineDate advocateOfferDeadlineDate;
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

    ElmntCreateNewIncentiveButton createNewIncentiveButton;
    ArrayList<DrivenElement> incentiveTypeItems;


    public PageCampaignRules(){
//        navigationMenu = new CampaignNavigationMenu();
        saveChangesButton = new ElmntSaveButton();
        campaignNameInput = new ElmntCampaignNameInput();
        campaignDescription = new ElmntCampaignDescriptionInput();
        advocateOfferDeadlineDate = new ElmntAdvocateOfferDeadlineDate();
        friendOfferDeadlineDate = new ElmntFriendOfferDeadlineDate();
        adOfferDeadlineHours = new ElmntAdOfferDeadlineHours();
        adOfferDeadlineMinutes = new ElmntAdOfferDeadlineMinutes();
        frOfferDeadlineHours = new ElmntFrOfferDeadlineHours();
        frOfferDeadlineMinutes = new ElmntFrOfferDeadlineMinutes();
    }



    private PageCampaignRules saveChanges(){
        saveChangesButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(saveChangesButton.getLocator(), "Saving..."));
        saveChangesButton = new ElmntSaveButton();
        wait.until(ExpectedConditions.textToBePresentInElement(saveChangesButton.getWebElement(), "Save changes"));
        Log.changesAreSaved();

        return new PageCampaignRules();
    }

    public PageCampaignRules setCampaignName(String newName){
        campaignNameInput.clear();
        campaignNameInput.sendKeys(newName);
        return saveChanges();
    }

    public String getCampaignName(){
        return campaignNameInput.getText();
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
        return advocateOfferDeadlineDate.getText();
    }

    public String getFriendDeadlineDate(){
        return friendOfferDeadlineDate.getText();
    }

    //Not completed yet !!!!!
    public PageCampaignRules createNewIncentive(IncentiveType incentiveType, int rewardAmount, DiscountType discountType, CouponCodeType couponCodeType){
        createNewIncentiveButton = new ElmntCreateNewIncentiveButton();
        createNewIncentiveButton.click();
        PopupIncentiveFactory incentivePopup = selectIncentive(incentiveType);
        return incentivePopup.createIncentive(rewardAmount, discountType, couponCodeType);
    }

   /*Note: Format of dates should be MM/DD/YYYY,
   * Hours and Minute format should be equal to values from relevant dropdown lists on UI(Rules page)
   * */
    public PageCampaignRules setDeadlineDates(String advocateOfferDeadlineDate, String adHours, String adMinute, String friendOfferDeadlineDate, String frHour, String frMinute){
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

    private PopupIncentiveFactory selectIncentive(IncentiveType incentiveType){
        ArrayList<DrivenElement> incentiveTypeItems = new ElmntIncentiveTypeItem().getElements();
        String incentiveName = getIncentiveTypeName(incentiveType);
        selectByText(incentiveName, incentiveTypeItems);

        if(incentiveType == IncentiveType.AdvocateReferralIncentive || incentiveType == IncentiveType.AdvocateSharingIncentive){
            return new PopupExtendedIncentive();
        }
        else {
            return new PopupIncentive();
        }



    }

    private String getIncentiveTypeName(IncentiveType incentiveType){
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
