package talkable.talkableSite.campaign.pages.campaignDetailsPage;

import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.talkableSite.reports.purchasesReport.createNewPurchasePage.CreateNewPurchasePage;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.containers.copyCampaignPopup.CopyCampaignPopup;

public class CampaignDetailsPage extends AbstractCampaignPage {

//    public CampaignNavigationMenu campaignNavigationMenu;
//    public Header header;
    private ElmntCopyButton copyButton;
    private ElmntCreateTestOfferButton createTestOfferButton;
    private ElmntAdvocateOffersCount advocateOffers;

    private ElmntDeleteButton deleteButton;//should be initialised ONLY inside of the relevant method()


    public CampaignDetailsPage(){
//        header = new Header();
//        campaignNavigationMenu = new CampaignNavigationMenu();
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

    public String getAdvocateOffersTotalCount(){
        if (isElementPresent(advocateOffers)) {
            advocateOffers = new ElmntAdvocateOffersCount();
            return advocateOffers.getText();
        }
        else{
            System.out.println("Advocate Offer Count is empty");
            return "Total: 0";
        }
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