package talkable.campaign.pages.campaignDetailsPage;

import abstractObjects.AbstractElementsContainer;
import talkable.campaign.pages.campaignDetailsPage.containers.copyCampaignPopup.CopyCampaignPopup;
import talkable.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;
import talkable.campaign.pages.createNewPurchasePage.CreateNewPurchasePage;

public class CampaignDetailsPage extends AbstractElementsContainer {

    public CampaignNavigationMenu campaignNavigationMenu;
    private ElmntCopyButton copyButton;
    private ElmntCreateTestOfferButton createTestOfferButton;

    private ElmntDeleteButton deleteButton;//should be initialised ONLY inside of the relevant method()


    public CampaignDetailsPage(){
        campaignNavigationMenu = new CampaignNavigationMenu();
        copyButton = new ElmntCopyButton();
        createTestOfferButton = new ElmntCreateTestOfferButton();
    }

    public CampaignDetailsPage copyCampaign(String newCampaignName){
        copyButton.click();
        CopyCampaignPopup copyCampaignPopup = new CopyCampaignPopup();
        return copyCampaignPopup.copyCampaign(newCampaignName);
    }

    public CampaignDetailsPage copyCampaign(){
        copyButton.click();
        CopyCampaignPopup copyCampaignPopup = new CopyCampaignPopup();
        return copyCampaignPopup.copyCampaign();
    }

    public String getCampaignName(){
        return campaignNavigationMenu.getCampaignName();
    }

    public String getCampaignType(){
        return campaignNavigationMenu.getCampaignType();
    }

    public CreateNewPurchasePage clickCreateTestOffer(){
        createTestOfferButton.click();
        return new CreateNewPurchasePage();
    }




}
