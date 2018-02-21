package talkable.campaign.pages.campaignNavigationMenu;


import org.testng.Assert;
import talkable.campaign.pages.campaignNavigationMenu.elements.*;
import talkable.campaign.pages.launchCampaignPage.LaunchCampaignPage;

public class CampaignNavigationMenu extends CampaignNavigationMenuOnEditor{

    private LaunchDeactivateCampaignButton launchDeactivateCampaignButton;
    private ElmntCampaignName campaignName;
    private ElmntCampaignType campaignType;

    public CampaignNavigationMenu(){
        //        initialization of containers:
        super();
        launchDeactivateCampaignButton = new LaunchDeactivateCampaignButton();
        campaignName = new ElmntCampaignName();
        campaignType = new ElmntCampaignType();
    }

    public LaunchCampaignPage launchCampaign(){
        if(isCampaignActive()){
            Assert.fail("You can not launch this campaign. It is already active. ");
        }
        launchDeactivateCampaignButton.click();
        return new LaunchCampaignPage();

    }

    public CampaignNavigationMenu deactivateCampaign(){
        if(!isCampaignActive()){
            Assert.fail("You can not deactivate this campaign. It is not active.");
        }
        launchDeactivateCampaignButton.click();
        return new CampaignNavigationMenu();
    }

    private boolean isCampaignActive(){
        return getCampaignStatus().equals("Status: Live");
    }

    public String getCampaignName(){
        return campaignName.getText();
    }

    public String getCampaignType(){
        return campaignType.getText();
    }







}
