package talkable.talkableSite.campaign.pages.launchCampaignPage;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnLaunchCampaignPage;
import talkable.talkableSite.campaign.pages.launchCampaignPage.elements.CancelButton;
import talkable.talkableSite.campaign.pages.launchCampaignPage.elements.LaunchCampaignButton;
import talkable.talkableSite.campaign.pages.launchCampaignPage.noIntegrationFoundPopup.NoIntegrationFoundPopup;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;

public class LaunchCampaignPage extends AbstractTalkableSitePage {

    public CampaignNavigationMenuOnLaunchCampaignPage campaignNavigationMenu = new CampaignNavigationMenuOnLaunchCampaignPage();
    private LaunchCampaignButton launchCampaignButton= new LaunchCampaignButton();
    private NoIntegrationFoundPopup noIntegrationFoundPopup;
    private CancelButton cancelButton = new CancelButton();
    private ElmntAlertIntegrationMsg alertIntegrationMsg;


    public LaunchCampaignPage(){
//        launchCampaignButton = new LaunchCampaignButton();
//        cancelButton = new CancelButton();
    }

    public CampaignDetailsPage launchCampaign(){
        if(!isIntegrated()){
            launchCampaignButton.click();
            System.out.println("DEBUG: No Integration message is displayed present");
            noIntegrationFoundPopup = new NoIntegrationFoundPopup();
            noIntegrationFoundPopup.launchNowButton.click();
        }
        else{
            launchCampaignButton.click();
        }
//        try{
//            noIntegrationFoundPopup = new NoIntegrationFoundPopup();
//            noIntegrationFoundPopup.launchNowButton.click();
//        }
//        catch (NoSuchElementException e ){
//
//            Log.popupIsNotOpenedMsg();
//            System.out.println(e);
//        }

        return new CampaignDetailsPage();
    }

    private boolean isIntegrated(){
        return !isElementPresent(ElmntAlertIntegrationMsg.getStaticLocator());
    }

    public void cancel(){
        cancelButton.click();
    }



}
