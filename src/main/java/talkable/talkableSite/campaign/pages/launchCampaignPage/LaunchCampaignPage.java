package talkable.talkableSite.campaign.pages.launchCampaignPage;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnLaunchCampaignPage;
import talkable.talkableSite.campaign.pages.launchCampaignPage.elements.CancelButton;
import talkable.talkableSite.campaign.pages.launchCampaignPage.elements.LaunchCampaignButton;
import talkable.talkableSite.campaign.pages.launchCampaignPage.noIntegrationFoundPopup.NoIntegrationFoundPopup;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import util.DriverConfig;

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
//            launchCampaignButton.click();
            launchIntegratedCampaign();
        }
        return new CampaignDetailsPage();
    }

    public CampaignDetailsPage launchIntegratedCampaign(){
        launchCampaignButton.click();
        return new CampaignDetailsPage();
    }

    private boolean isIntegrated(){
        try{
            new DriverConfig()
                    .getCustomWait(5,500)
                    .until(ExpectedConditions.numberOfElementsToBe(ElmntAlertIntegrationMsg.getStaticLocator(), 1));
            return false;
        }catch (TimeoutException e){
            System.out.println("DEBAG: Integration popup is not displayed");
            return true;
        }
//        return !isElementPresent(ElmntAlertIntegrationMsg.getStaticLocator());
    }

    public void cancel(){
        cancelButton.click();
    }



}
