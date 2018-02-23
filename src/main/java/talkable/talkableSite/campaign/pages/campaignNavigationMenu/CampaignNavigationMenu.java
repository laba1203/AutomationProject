package talkable.talkableSite.campaign.pages.campaignNavigationMenu;


import org.testng.Assert;
import talkable.talkableSite.campaign.pages.launchCampaignPage.LaunchCampaignPage;

public class CampaignNavigationMenu extends CampaignNavigationMenuOnEditor{

    private ElmntLaunchDeactivateCampaignButton elmntLaunchDeactivateCampaignButton;
    private ElmntCampaignName campaignName;
    private ElmntCampaignType campaignType;

    public CampaignNavigationMenu(){
        //        initialization of containers:
        super();
        elmntLaunchDeactivateCampaignButton = new ElmntLaunchDeactivateCampaignButton();
        campaignName = new ElmntCampaignName();
        campaignType = new ElmntCampaignType();
    }

    public LaunchCampaignPage launchCampaign(){
        if(isCampaignActive()){
            Assert.fail("You can not launch this campaign. It is already active. ");
        }
        elmntLaunchDeactivateCampaignButton.click();
        return new LaunchCampaignPage();

    }

    public CampaignNavigationMenu deactivateCampaign(){
        if(!isCampaignActive()){
            Assert.fail("You can not deactivate this campaign. It is not active.");
        }
        elmntLaunchDeactivateCampaignButton.click();
        return new CampaignNavigationMenu();
    }

    public boolean isCampaignActive(){
        return getCampaignStatus().equals("Status: Live");
    }

    public String getCampaignName(){
        return campaignName.getText();
    }

    public String getCampaignType(){
        return campaignType.getText();
    }







}
