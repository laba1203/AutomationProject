package talkable.launchCampaignPage;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import talkable.launchCampaignPage.elements.CancelButton;
import talkable.launchCampaignPage.elements.LaunchCampaignButton;
import talkable.launchCampaignPage.noIntegrationFoundPopup.NoIntegrationFoundPopup;
import util.logging.Log;

public class LaunchCampaignPage extends AbstractElementsContainer {

    private Log log = new Log();

    private LaunchCampaignButton launchCampaignButton;
    private NoIntegrationFoundPopup noIntegrationFoundPopup;
    private CancelButton cancelButton;


    public LaunchCampaignPage(){
        launchCampaignButton = new LaunchCampaignButton();
        cancelButton = new CancelButton();

    }

    public void launchCampaign(){
        launchCampaignButton.click();
        try{
            noIntegrationFoundPopup = new NoIntegrationFoundPopup();
            noIntegrationFoundPopup.launchNowButton.click();
        }
        catch (NoSuchElementException e ){

            log.popupIsNotOpenedMsg(noIntegrationFoundPopup);
            System.out.println(e);
        }
    }

    public void cancel(){
        cancelButton.click();
    }



}
