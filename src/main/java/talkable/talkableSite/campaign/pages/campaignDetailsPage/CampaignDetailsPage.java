package talkable.talkableSite.campaign.pages.campaignDetailsPage;

import org.testng.Assert;
import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.reports.newAffiliateMember.PageNewAffiliateMember;
import talkable.talkableSite.reports.purchasesReport.createNewPurchasePage.CreateNewPurchasePage;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.containers.copyCampaignPopup.CopyCampaignPopup;

public class CampaignDetailsPage extends AbstractCampaignPage {

    private ElmntCopyButton copyButton;
    private ElmntCreateTestOfferButton createTestOfferButton;
    private ElmntAdvocateOffersCount advocateOffers;

    private ElmntDeleteButton deleteButton;//should be initialised ONLY inside of the relevant method()


    public CampaignDetailsPage() {
//        header = new Header();
//        campaignNavigationMenu = new CampaignNavigationMenu();
        copyButton = new ElmntCopyButton();
        createTestOfferButton = new ElmntCreateTestOfferButton();
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
            advocateOffers = new ElmntAdvocateOffersCount();
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

}






