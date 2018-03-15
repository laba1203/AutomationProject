package talkable.talkableSite.campaign.pages.detailsPage;

import abstractObjects.Alert;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.reports.newAffiliateMember.PageNewAffiliateMember;
import talkable.talkableSite.reports.purchasesReport.createNewPurchasePage.CreateNewPurchasePage;
import talkable.talkableSite.campaign.pages.detailsPage.containers.copyCampaignPopup.CopyCampaignPopup;

public class CampaignDetailsPage extends AbstractCampaignPage {

    private ElmntCopyButton copyButton;
    private ElmntCreateTestOfferButton createTestOfferButton;

    public CampaignDetailsPage() {
//        header = new Header();
//        campaignNavigationMenu = new CampaignNavigationMenu();
        copyButton = new ElmntCopyButton();
        if(campaignNavigationMenu.getCampaignPlacement() != CampaignPlacement.Gleam) {
            createTestOfferButton = new ElmntCreateTestOfferButton();
        }

    }

    public CampaignDetailsPage copyCampaign(String newCampaignName) {
        copyButton.click();
        CopyCampaignPopup copyCampaignPopup = new CopyCampaignPopup();
        return copyCampaignPopup.copyCampaign(newCampaignName);
    }

    public CampaignDetailsPage copyCampaign() {
        copyButton.click();
        CopyCampaignPopup copyCampaignPopup = new CopyCampaignPopup();
        return copyCampaignPopup.copyCampaign();
    }

    public String getAdvocateOffersTotalCount() {
        if (isElementPresent(ElmntAdvocateOffersCount.getStaticLocator())) {
            ElmntAdvocateOffersCount advocateOffers = new ElmntAdvocateOffersCount();
            return advocateOffers.getText();
        } else {
            System.out.println("Advocate Offer Count is empty");
            return "Total: 0";
        }

//        try{
//            advocateOffers = new ElmntAdvocateOffersCount();
//            return advocateOffers.getText();
//        }
//        catch (NullPointerException e){
//            System.out.println("Advocate Offer Count is empty. Element with Offers count is not found");
//            return "Total: 0";
//        }
    }


    public CreateNewPurchasePage clickCreateTestOfferForPostPurchase() {
        if (campaignNavigationMenu.getCampaignPlacement() != CampaignPlacement.PostPurchase) {
            Assert.fail("FAILED: Post Purchase method is used for Post Purchase campaign");
        }
        createTestOfferButton.click();
        return new CreateNewPurchasePage();
    }

    public PageNewAffiliateMember clickCreateTestOfferForNonPostPurchase() {
        if (campaignNavigationMenu.getCampaignPlacement() == CampaignPlacement.PostPurchase) {
            Assert.fail("FAILED: You can not use this method for Post Purchase campaign");
        }
        createTestOfferButton.click();
        return new PageNewAffiliateMember();
    }

    public PageCampaigns delete(){
        new ElmntDeleteButton().click();
        new Alert().confirm();
        return new PageCampaigns();
    }

}






