package talkable.talkableSite.campaign.pages.launchCampaignPage;

import org.openqa.selenium.NoSuchElementException;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnLaunchCampaignPage;
import talkable.talkableSite.campaign.pages.launchCampaignPage.elements.CancelButton;
import talkable.talkableSite.campaign.pages.launchCampaignPage.elements.LaunchCampaignButton;
import talkable.talkableSite.campaign.pages.launchCampaignPage.noIntegrationFoundPopup.NoIntegrationFoundPopup;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import util.logging.Log;

public class LaunchCampaignPage extends AbstractTalkableSitePage {

    public CampaignNavigationMenuOnLaunchCampaignPage campaignNavigationMenu = new CampaignNavigationMenuOnLaunchCampaignPage();
    private LaunchCampaignButton launchCampaignButton= new LaunchCampaignButton();
    private NoIntegrationFoundPopup noIntegrationFoundPopup;
    private CancelButton cancelButton = new CancelButton();


    public LaunchCampaignPage(){
//        launchCampaignButton = new LaunchCampaignButton();
//        cancelButton = new CancelButton();
    }

    public CampaignDetailsPage launchCampaign(){
        launchCampaignButton.click();
        try{
            noIntegrationFoundPopup = new NoIntegrationFoundPopup();
            noIntegrationFoundPopup.launchNowButton.click();
        }
        catch (NoSuchElementException e ){

            Log.popupIsNotOpenedMsg();
            System.out.println(e);
        }

        return new CampaignDetailsPage();
    }

    public void cancel(){
        cancelButton.click();
    }



}
