package common.cases;

import org.testng.Assert;
import talkable.addYourSitePage.AddSitePage;
import talkable.createNewCampaignPage.CreateNewCampaignPage;
import talkable.headerFrame.Header;
import talkable.menuFrame.MenuFrame;

import static talkable.createNewCampaignPage.CreateNewCampaignPage.CampaignType.AdvocateDashboard;
import static talkable.createNewCampaignPage.CreateNewCampaignPage.PlacementType.FloatingWidget;

/*Class to allocate common scenarios in Talkable.
 * */
public class CommonScenarios {


    /***
     * Common scenario to create new campaign with default values
     * Precondition: Any page with header frame (class = Header()) should be opened
     * */
    public void createNewSite(String siteName, String url){
        Header header = new Header();
        header.menuButton.click();

        MenuFrame menuFrame = new MenuFrame();
        menuFrame.createNewSiteButton.click();

        AddSitePage addSitePage = new AddSitePage();
        addSitePage.populateForm(siteName, url);

        Assert.assertEquals(header.siteSelectButton.getText(), siteName);
    }



    /***
     * Scenario to initiate campaign creation.
     * Precondition: Header should be available
     * Result: Campaign Details page of newly created campaign(as per input parameters)
     * */
    public void initiateCampaignCreation(CreateNewCampaignPage.CampaignType campaignType, CreateNewCampaignPage.PlacementType placementType){
        Header header = new Header();
        header.menuButton.click();
        MenuFrame menuFrame = new MenuFrame();
        menuFrame.createNewCampaignButton.click();
        //Create New Campaign page:
        CreateNewCampaignPage createNewCampaignPage = new CreateNewCampaignPage();
        createNewCampaignPage.createCampaign(campaignType, placementType);

    }
}
