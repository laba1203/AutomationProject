package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;

import java.util.Date;
import java.util.List;

public class CampaignRulesPage extends AbstractElementsContainer{

    public CampaignNavigationMenu navigationMenu;
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
    enum DiscountType{ Percentage, FixedAmount}
    ElmntCreateNewIncentiveButton createNewIncentiveButton;
    List<ElmntIncentiveTypeItem> incentiveTypeItems;


    public CampaignRulesPage(){
        navigationMenu = new CampaignNavigationMenu();
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



    private CampaignRulesPage saveChanges(){
        saveChangesButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(saveChangesButton.getWebElement(), "Save changes"));
        return new CampaignRulesPage();
    }

    public CampaignRulesPage updateCampaignName(String newName){
        campaignNameInput.clear();
        campaignNameInput.sendKeys(newName);
        return saveChanges();
    }

    public CampaignRulesPage setCampaignDescription(String text){
        campaignDescription.sendKeys(text);
        return saveChanges();
    }

    public void createNewAdvocateIncentive(/*String rewardAmount, DiscountType discountType, String couponCode*/){
        createNewIncentiveButton = new ElmntCreateNewIncentiveButton();
        createNewIncentiveButton.click();
//        incentiveTypeItems = driver.findElements(new ElmntIncentiveTypeItem().getLocator());
    }


   /*Note: Format of dates should be MM/DD/YYYY,
   * Hours and Minute format should be equal to values from relevant dropdown lists on UI(Rules page)
   * */
    public CampaignRulesPage setDeadlineDates(String advocateOfferDeadlineDate, String adHours, String adMinute, String friendOfferDeadlineDate, String frHour, String frMinute){
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








}
