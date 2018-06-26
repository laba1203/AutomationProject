package talkable.talkableSite.campaign.pages.detailsPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.common.elements.alert.Alert;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.common.CampaignPlacement;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.reports.newAffiliateMember.PageNewAffiliateMember;
import talkable.talkableSite.reports.purchases.createNewPurchasePage.CreateNewPurchasePage;
import talkable.talkableSite.campaign.pages.detailsPage.containers.copyCampaignPopup.CopyCampaignPopup;
import util.logging.Log;

public class CampaignDetailsPage extends AbstractCampaignPage {

    private static final By flushAllDataLctr = By.xpath("//input[@value='Flush all data']");

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
    }


    public CreateNewPurchasePage clickCreateTestOfferForPostPurchase() {
        if (campaignNavigationMenu.getCampaignPlacement() != CampaignPlacement.PostPurchase) {
            Assert.fail("FAILED: Post Purchase method is used for Post Purchase campaign");
        }
        createTestOfferButton.click();
        return new CreateNewPurchasePage();
    }

    public PageNewAffiliateMember clickCreateTestOfferNewAffiliateMember() {
        if (campaignNavigationMenu.getCampaignPlacement() == CampaignPlacement.PostPurchase) {
            Assert.fail("FAILED: You can not create Affiliate Member for Post Purchase campaign");
        }
        createTestOfferButton.click();
        return new PageNewAffiliateMember();
    }

    public PageCampaigns delete(){
        new ElmntDeleteButton().click();
        new Alert().confirm();
        return new PageCampaigns();
    }

    public CampaignDetailsPage flushAllData(){
        new Element(flushAllDataLctr, "'Flush all data' button").click();
        new Alert().confirm();
        new MsgCampaignDataFlushedNotification();
        Log.logRecord("Campaign data was successfully flushed");
        return new CampaignDetailsPage();
    }

//    Campaign data was successfully flushed

}






