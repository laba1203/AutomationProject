package talkable.talkableSite.campaign.pages.launchCampaignPage.noIntegrationFoundPopup;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.campaign.pages.launchCampaignPage.noIntegrationFoundPopup.elements.CancelButton;
import talkable.talkableSite.campaign.pages.launchCampaignPage.noIntegrationFoundPopup.elements.LaunchNowButton;

public class NoIntegrationFoundPopup extends AbstractElementsContainer{
    public LaunchNowButton launchNowButton;
    public CancelButton cancelButton;

    public NoIntegrationFoundPopup(){
        launchNowButton = new LaunchNowButton();
//        cancelButton = new CancelButton();
    }

}
