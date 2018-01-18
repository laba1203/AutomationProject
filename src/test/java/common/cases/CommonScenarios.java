package common.cases;

import org.testng.Assert;
import talkable.addYourSitePage.AddSitePage;
import talkable.campaignNavigationMenu.CampaignNavigationMenu;
import talkable.createNewCampaignPage.CreateNewCampaignPage;
import talkable.headerFrame.Header;
import talkable.homePage.HomePage;
import talkable.launchCampaignPage.LaunchCampaignPage;
import talkable.loginPage.LoginPage;
import talkable.menuFrame.MenuFrame;

/*Class to allocate common scenarios in Talkable.
 * */
public class CommonScenarios {

    /***
     *Scenario to login into Talkable
     * Precondition: Talkable home page should be opened
     * 1. Click 'Login' button --> Login page is opened
     * 2. Populate login and password and click Login
     * Post-condition: User logged to Talkable. Header should be available for further actions*/
    public void login(String email, String password){
        HomePage homePage = new HomePage();
        homePage.loginButton.click();
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(email, password);
    }


    /***
     * Common scenario to create new campaign with default values
     * Precondition: Any page with header frame (class = Header()) should be opened
     * Post-condition: Site created. Site details page is opened*/
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
     * Post-condition: Campaign Details page of newly created campaign(as per input parameters)
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

    /***
     * Scenario to launch campaign
     * Precondition: initateCampaignCreation() should be already executed OR Page with Launch Button should be displayed
     * Post-condition: Campaign  status changed to Active. Campaign Details page is opened*/
    public void launchCampaign(){
        CampaignNavigationMenu campaignNavigationMenu = new CampaignNavigationMenu();
        campaignNavigationMenu.launchCampaignButton.click();
        //Launch Campaign Page is opened
        LaunchCampaignPage launchCampaignPage = new LaunchCampaignPage();
        launchCampaignPage.launchCampaign();

        Assert.assertEquals(new CampaignNavigationMenu().getCampaignStatus(), "Status: Live");
    }

}
