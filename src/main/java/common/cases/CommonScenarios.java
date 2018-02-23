package common.cases;

import org.testng.Assert;
import talkable.talkableSite.IntegrationInstructionPage.IntegrationInstructionPage;
import talkable.addYourSitePage.AddSitePage;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;
import talkable.talkableSite.reports.purchasesReport.createNewPurchasePage.CreateNewPurchasePage;
import talkable.talkableSite.createNewCampaignPage.CreateNewCampaignPage;
import talkable.talkableSite.headerFrame.Header;
import talkable.homePage.HomePage;
import talkable.talkableSite.campaign.pages.launchCampaignPage.LaunchCampaignPage;
import talkable.loginPage.LoginPage;
import talkable.userRegistration.chosePlatformPage.ChosePlatformPage;
import talkable.userRegistration.createAccountPage.CreateAccountPage;
import util.logging.Log;

/*Class to allocate common scenarios in Talkable.
 * */
public class CommonScenarios {

    private static final String liveStatusActive = "Status: Live";
    private static final String liveStatusTest = "Status: Test";

    /***
     *Scenario to login into Talkable
     * Precondition: Talkable home page should be opened
     * 1. Click 'Login' button --> Login page is opened
     * 2. Populate login and password and click Login
     * Post-condition: User logged to Talkable. Header should be available for further actions
     * */
    public static Header login(String email, String password){
        HomePage homePage = new HomePage();
//        homePage.loginButton.click();
//        LoginPage loginPage = new LoginPage();
        LoginPage loginPage = homePage.clickLoginButton();
        Header header = loginPage.submitLoginForm(email, password);
        return header;
    }

    /***
     * Common scenario to create new campaign with default values
     * Precondition: Any page with header frame (class = Header()) should be opened
     * Post-condition: Site created. Site details page is opened
     * */
    public void createNewSite(String siteName, String url){
        Header header = new Header();
//        header.menuButton.click();
        AddSitePage addSitePage = header.openMenu().clickCreateNewSiteButton();

//        MenuFrame menuFrame = new MenuFrame();
//        menuFrame.createNewSiteButton.click();
//        AddSitePage addSitePage = new AddSitePage();

        IntegrationInstructionPage integrationInstructionPage = addSitePage.submitForm(siteName, url);
        Assert.assertEquals(integrationInstructionPage.header.getSiteName(), siteName);
    }

    /***
     * Scenario to initiate campaign creation.
     * Precondition: Header should be available. Site should not have any active campaign with the selected campaignType/placementType
     * Post-condition: Campaign Details page of newly created campaign(as per input parameters)
     * */
    public static CampaignDetailsPage initiateCampaignCreation(CreateNewCampaignPage.CampaignType campaignType, CreateNewCampaignPage.PlacementType placementType){
        Header header = new Header();
        CreateNewCampaignPage createNewCampaignPage = header.openMenu().clickCreateNewCampaignButton();

//        header.menuButton.click();
//        MenuFrame menuFrame = new MenuFrame();
//        menuFrame.createNewCampaignButton.click();

        //Create New Campaign page:
//        CreateNewCampaignPage createNewCampaignPage = new CreateNewCampaignPage();
        createNewCampaignPage.createCampaign(campaignType, placementType);
        //check Campaign Status
        Assert.assertEquals(new CampaignNavigationMenu().getCampaignStatus(), liveStatusTest);
        return new CampaignDetailsPage();
    }


    /***
     * Scenario to launch campaign
     * Precondition: initateCampaignCreation() should be already executed OR Page with Launch Button should be displayed
     * Post-condition: Campaign  status changed to Active. Campaign Details page is opened
     * */
    public static CampaignDetailsPage launchCampaign()
    {
        CampaignNavigationMenu campaignNavigationMenu = new CampaignNavigationMenu();
//        campaignNavigationMenu.launchDeactivateCampaignButton.click();
        LaunchCampaignPage launchCampaignPage = campaignNavigationMenu.launchCampaign();

        //Launch Campaign Page is opened
//        LaunchCampaignPage launchCampaignPage = new LaunchCampaignPage();
        CampaignDetailsPage campaignDetailsPage = launchCampaignPage.launchCampaign();
        //check Campaign Status
        Assert.assertEquals(campaignDetailsPage.campaignNavigationMenu.getCampaignStatus(), liveStatusActive);
        return new CampaignDetailsPage();
    }


    /***
     * Scenario to register new User and create new Site
     * Precondition: Register page should be opened(...talkable.com/register?object_or_array)
     * 1. Chose platform
     * 2. Populate registration form
     * 3. Verify that site is created.
     * Post-condition: IntegrationInstructionPage is opened.
     * Returns: user email.
     * */
    public String registerNewUserWithSite(String email, String password, String siteName, String siteUrl, ChosePlatformPage.PlatformType platformType)
    {
        CreateAccountPage createAccountPage =  new ChosePlatformPage().selectPlatform(platformType);
        IntegrationInstructionPage integrationInstructionPage = createAccountPage.populateAndSubmitForm(email, password, siteName, siteUrl);
//        Verify that site is created:
//        IntegrationInstructionPage integrationInstructionPage = new IntegrationInstructionPage();
        Assert.assertEquals(integrationInstructionPage.header.getSiteName(), siteName);
        Log.userAndSiteCreatedMsg(email, siteName);

        return email;
    }

    /*Scenarios to create Test Offer for campaign.
    * Precondition: Campaign Details page should be opened.
    * 1. Click Create Test Offer button
    * 2. Click Create Origin button with default values on Create Test Offer page
    * 3. Switch back to Campaign Details page
    * Returns: Campaign Details Page for parent campaign.
    * */
    public CampaignDetailsPage createTestOffer() {
        CampaignDetailsPage detailsPage = new CampaignDetailsPage();

        CreateNewPurchasePage createNewPurchasePage = detailsPage.clickCreateTestOffer();
        return createNewPurchasePage.createOfferAndSwitchToCampaign();
    }


}
