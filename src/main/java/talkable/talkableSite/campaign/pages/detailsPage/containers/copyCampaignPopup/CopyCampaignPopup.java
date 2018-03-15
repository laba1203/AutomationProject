package talkable.talkableSite.campaign.pages.detailsPage.containers.copyCampaignPopup;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;

public class CopyCampaignPopup extends AbstractElementsContainer{

    private ElmntCampaignNameInput campaignName;
    private ElmntCopyButton copyButton;

    public CopyCampaignPopup(){
        campaignName = new ElmntCampaignNameInput();
        copyButton = new ElmntCopyButton();
    }

    public CampaignDetailsPage copyCampaign(String newCampaignName){
        campaignName.clear();
        campaignName.sendKeys(newCampaignName);
        copyButton.click();
        return new CampaignDetailsPage();
    }

    public CampaignDetailsPage copyCampaign(){
        copyButton.click();
        return new CampaignDetailsPage();
    }



}
