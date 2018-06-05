package common.cases;

import api.objects.Site;
import org.testng.Assert;
import talkable.common.elements.pagination.Pagination;
import talkable.talkableSite.IntegrationInstructionPage.IntegrationInstructionPage;
import talkable.addYourSitePage.AddSitePage;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.campaignsPage.Table;
import talkable.talkableSite.reports.newAffiliateMember.PageNewAffiliateMember;
import talkable.talkableSite.reports.purchasesReport.createNewPurchasePage.CreateNewPurchasePage;
import talkable.talkableSite.createNewCampaignPage.CreateNewCampaignPage;
import talkable.talkableSite.headerFrame.Header;
import talkable.homePage.HomePage;
import talkable.talkableSite.campaign.pages.launchCampaignPage.LaunchCampaignPage;
import talkable.loginPage.LoginPage;
import talkable.talkableSite.siteDashboardPage.SiteDashboardPage;
import talkable.talkableSite.siteSettings.basic.SiteSettingsBasicTab;
import talkable.userRegistration.chosePlatformPage.ChosePlatformPage;
import talkable.userRegistration.createAccountPage.CreateAccountPage;
import util.DriverConfig;
import util.EnvFactory;
import util.logging.Log;

import static talkable.talkableSite.campaignsPage.Table.Status.DISABLED;
import static talkable.talkableSite.campaignsPage.Table.Status.LIVE;

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
    public static Header login(String email, String password) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLoginButton();
        Header header = loginPage.submitLoginForm(email, password);
        Log.logRecord("User logged into Talkable. Email = <" + email + ">");
        return header;
    }

    public static void logout(){
        new Header().openMenu().clickLogout();
    }

    /***
     * Common scenario to create new campaign with default values
     * Precondition: Any page with header frame (class = Header()) should be opened
     * Post-condition: Site created. Site details page is opened
     * */
    public static IntegrationInstructionPage createNewSite(String siteName, String url) {
        Header header = new Header();
        AddSitePage addSitePage = header.openMenu().clickCreateNewSiteButton();
        IntegrationInstructionPage integrationInstructionPage = addSitePage.submitForm(siteName, url);
        Assert.assertEquals(getSiteNameFromHeader(), siteName);
        Log.logRecord("New site successfully created. Site name = " + siteName);
        return integrationInstructionPage;
    }

    public static void navigateToAdminUrl(){
        DriverConfig.getDriver().navigate().to(EnvFactory.getAdminUrl());
        new Header();
        Log.logRecord("Navigated to admin URL");
    }

    public static SiteDashboardPage switchToIntegratedSiteByVisibleText(String siteName) {
        new Header().selectByVisibleText(siteName);
        SiteDashboardPage siteDashboardPage = new SiteDashboardPage().verifySiteName(siteName);

        Assert.assertEquals(getSiteNameFromHeader(), siteName, "FAILED: Site is not switched");

        Log.siteSwitchedMsg(siteName);
        return siteDashboardPage;
    }

    public static String getSiteNameFromHeader(){
        String name = new Header().getSiteName();
        Log.logRecord("Site name retrieved from Header : " + name);
        return name;
    }

    public static IntegrationInstructionPage switchToNonIntegratedSiteByVisibleText(String siteName) {
        new Header().selectByVisibleText(siteName);
        IntegrationInstructionPage integrationInstructionPage = new IntegrationInstructionPage();
        Assert.assertEquals(getSiteNameFromHeader(), siteName, "FAILED: Site is not switched");
        Log.siteSwitchedMsg(siteName);

        return integrationInstructionPage;
    }



    public static PageCampaigns openCampaignsPage() {
        PageCampaigns page = new Header().openCampaignsPage();
        Log.logRecord("Campaigns Page is opened");
        return page;
    }

    public static String getCampaignsCountFromCampaignsPage(Table.Status status){
        try {
            return new PageCampaigns().getCampaignsTable(status).getCampaignsCount();
        }catch (AssertionError e){
            return "0";
        }
    }


    public static void openCampaignDetailsPage(String campaignName, Table.Status campaignStatus){
        PageCampaigns page = openCampaignsPage();
        page.openCampaignByName(campaignName, campaignStatus);
        Log.logRecord("Details page is opened for campaign <" + campaignName + "> with status = <" + campaignStatus + ">.");
    }

    public static void deleteCampaignFromDetailsPage(){
        new CampaignDetailsPage().delete();
        Log.logRecord("Campaign deleted");
    }


    public static PageCampaigns deactivateAllCampaigns() {
        return openCampaignsPage().deactivateAllLiveCampaigns();
    }

    public static PageCampaigns deleteAllCampaignsWithStatus(Table.Status status){
        return new Header().openCampaignsPage().deleteAllCampaignsWithStatus(status);
    }

    public static PageCampaignRules openCampaignRulesPage() {
        PageCampaignRules page = new CampaignNavigationMenu().openRulesPage();
        Log.logRecord("Campaign Rules page is opened");
        return page;
    }

    public static PageCampaignRules setCampaignNameOnRulesPage(String campaignName) {
        PageCampaignRules page = new PageCampaignRules().setCampaignName(campaignName);
        Assert.assertEquals(page.getCampaignName(), campaignName, "FAILED: Campaign name is not updated on Rules page");
        Log.logRecord("Campaign name changed to <" + campaignName + ">");
        return page;
    }

//    public static String getCampaignNameFromRulesPage(){
//        return new PageCampaignRules().getCampaignName();
//    }

    public static void setCampaignDescriptionOnRulesPage(String campaignDesc){
        PageCampaignRules page = new PageCampaignRules().setCampaignDescription(campaignDesc);
        Assert.assertEquals(page.getCampaignDescription(), campaignDesc, "FAILED: Campaign description is not updated on Rules page");
        Log.logRecord("Campaign description changed to <" + campaignDesc + ">");
    }

    public static void setDeadlinesOnRulesPage(String advocateOfferDeadlineDate, String adOfferEndTime, String friendDeadlineDate, String frOfferEndTime){
        PageCampaignRules rulesPage = new PageCampaignRules();
        rulesPage = rulesPage.setDeadlineDates(advocateOfferDeadlineDate, adOfferEndTime, friendDeadlineDate, frOfferEndTime);

        Assert.assertEquals(rulesPage.getAdvocateDeadlineDate(), advocateOfferDeadlineDate, "FAILED: Incorrect Advocate Deadline Date on Campaign Rules page");
        Assert.assertEquals(rulesPage.getAdvocateDeadlineTime(), adOfferEndTime, "FAILED: Incorrect Advocate Deadline Time on Campaign Rules page");
        Log.logRecord("Advocate deadline date and time have been changed to " + advocateOfferDeadlineDate + ", " + adOfferEndTime + "on Campaign Rules page");

        Assert.assertEquals(rulesPage.getFriendDeadlineDate(), friendDeadlineDate, "FAILED: Incorrect Friend Deadline Date on Campaign Rules page");
        Assert.assertEquals(rulesPage.getFriendDeadlineTime(), frOfferEndTime,  "FAILED: Incorrect Friend Deadline Time on Campaign Rules page");
        Log.logRecord("Friend deadline date and time have been changed to " + friendDeadlineDate + ", " + frOfferEndTime + "on Campaign Rules page");

    }


    /***
     * Scenario to initiate campaign creation.
     * Precondition: Header should be available. Site should not have any active campaign with the selected campaignType/placementType
     * Post-condition: Campaign Details page of newly created campaign(as per input parameters)
     * */
    public static CampaignDetailsPage initiateCampaignCreation(CampaignType campaignType, CampaignPlacement placementType) {
        Header header = new Header();
        CreateNewCampaignPage createNewCampaignPage = header.openMenu().clickCreateNewCampaignButton();
        //Create New Campaign page:
        CampaignDetailsPage campaignDetailsPage = createNewCampaignPage.createCampaign(campaignType, placementType);
        //check Campaign Status
        Assert.assertEquals(campaignDetailsPage.campaignNavigationMenu.getCampaignStatus(), liveStatusTest);
        Log.logRecord("New campaign is created. Campaign type = <" + campaignType + ">, placement = <" + placementType + ">");
        return campaignDetailsPage;
    }

    /***
     * Scenario to initiate campaign creation from Campaigns Page adn return campaign name.
     * Precondition: Header should be available. Site should not have any active campaign with the selected campaignType/placementType
     * @return campaign name
     * Post-condition: Campaign Details page of newly created campaign(as per input parameters)
     * */
    public static String createNewCampaignFromCampaignsPage(CampaignType campaignType, CampaignPlacement placement){
        CampaignDetailsPage detailsPage = new PageCampaigns()
                .createNewCampaign(campaignType, placement);
        Log.logRecord("New campaign is created. Campaign type = <" + campaignType + ">, placement = <" + placement + ">");
        return detailsPage.campaignNavigationMenu.getCampaignName();
    }

//    /***
//     * Scenario to initiate campaign creation from Campaigns Page..
//     * Precondition: Header should be available.
//     * Post-condition: Campaign Details page of newly created campaign(as per input parameters)
//     * */
//    public static CampaignDetailsPage initiateCampaignCreationFromCampaignsPage(CampaignType campaignType, CampaignPlacement placementType) {
//        return new Header().openCampaignsPage().createNewCampaign(campaignType, placementType);
//    }


    /***
     * Scenario to launch campaign
     * Precondition: initateCampaignCreation() should be already executed OR Page with Launch Button should be displayed
     * Post-condition: Campaign  status changed to Active. Campaign Details page is opened
     * */
    public static CampaignDetailsPage launchCampaign() {
        CampaignNavigationMenu campaignNavigationMenu = new CampaignNavigationMenu();
        LaunchCampaignPage launchCampaignPage = campaignNavigationMenu.clickLaunchButton();

        //Launch Campaign Page is opened
        CampaignDetailsPage campaignDetailsPage = launchCampaignPage.launchCampaign();
        //check Campaign Status
        Assert.assertEquals(campaignDetailsPage.campaignNavigationMenu.getCampaignStatus(), liveStatusActive);
        Log.logRecord("Campaign is launched. Campaign Name = " + campaignDetailsPage.campaignNavigationMenu.getCampaignName());
        return new CampaignDetailsPage();
    }

    /***
     * Scenario to launch campaign from any page with CampaignNavigation menu
     * Post-condition: Details page of launched campaign.
     * */
    public static CampaignDetailsPage createAndLaunchCampaign(CampaignType campaignType, CampaignPlacement placementType) {
        createNewCampaignFromCampaignsPage(campaignType, placementType);
        try {
            // This part is added due to an existing defect. Error 500 is returned when campaign is launched directly after creation.
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return launchCampaign();
    }

    public static String getAdvocateOfferTotalCountFromCampaignDetailsPage(){
        return new CampaignDetailsPage().getAdvocateOffersTotalCount();
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
    public static IntegrationInstructionPage registerNewUserWithSite(String email, String password, String siteName, String siteUrl, ChosePlatformPage.PlatformType platformType) {
        CreateAccountPage createAccountPage = new ChosePlatformPage().selectPlatform(platformType);
        IntegrationInstructionPage integrationInstructionPage = createAccountPage.populateAndSubmitForm(email, password, siteName, siteUrl);
//        Verify that site is created:
//        IntegrationInstructionPage integrationInstructionPage = new IntegrationInstructionPage();
        Assert.assertEquals(getSiteNameFromHeader(), siteName);
        Log.logRecord("New user is registered. User name = <" + email + ">. Site = <" + siteName + ">");

        return integrationInstructionPage;
    }

    /*Scenarios to create Test Offer for campaign with Post Purchase placement.
     * Precondition: Campaign Details page should be opened.
     * 1. Click Create Test Offer button
     * 2. Click Create Origin button with default values on Create Test Offer page
     * 3. Switch back to Campaign Details page
     * Returns: Campaign Details Page for parent campaign.
     * */
    public static CampaignDetailsPage createTestOfferNewPurchase() {
        CampaignDetailsPage detailsPage = new CampaignDetailsPage();

        CreateNewPurchasePage createNewPurchasePage = detailsPage.clickCreateTestOfferForPostPurchase();
        createNewPurchasePage.createOfferAndSwitchToCampaign();
        Log.logRecord("Test offer created (New Purchase)");
        return new CampaignDetailsPage();
    }

    /*Scenarios to create Test Offer for campaign with non Post Purchase placement (FW, SA, GR).
     * Precondition: Campaign Details page should be opened.
     * 1. Click Create Test Offer button
     * 2. Populate email on New Affiliated Member page
     * 3. Click Create Origin button
     * 4. Switch back to Campaign Details page
     * Returns: Campaign Details Page for parent campaign.
     * */
    public static CampaignDetailsPage createTestOfferNewAffiliateMember(String newAffiliatedMemberEmail) {
        CampaignDetailsPage detailsPage = new CampaignDetailsPage();
        PageNewAffiliateMember newAffiliateMember = detailsPage.clickCreateTestOfferNewAffiliateMember();

        newAffiliateMember.createMemberAndSwitchToCampaign(newAffiliatedMemberEmail);
        Log.logRecord("Test offer created (New Affiliate Member)");
        return new CampaignDetailsPage();
    }


    /*Scenarios to deactivate campaign.
     * Precondition: Page with header should opened. Campaign with campaignName should be active(Live).
     * 1. Navigate to Campaigns page
     * 2. Search campaign by @campaignName
     * 3. Select campaign
     * 4. Deactivate campaign.
     * @Returns: Campaign Details Page deactivated campaign.
     * */
    public static CampaignDetailsPage deactivateCampaign(String campaignName) {
        PageCampaigns campaignsPage = new Header().openCampaignsPage();
        CampaignDetailsPage detailsPage = campaignsPage.openCampaignByName(campaignName, LIVE);
        CampaignNavigationMenu menu = detailsPage.campaignNavigationMenu.deactivateCampaign();
        Assert.assertEquals(menu.getCampaignStatus(), "Status: Disabled", "FAILED: Campaign is not deactivated");
        Log.logRecord("Campaign <" + campaignName + "> is deactivated");

        return new CampaignDetailsPage();
    }

    public static void reactivateCampaignFromCampaignsPage(String campaignName){
        new PageCampaigns()
                .openCampaignByName(campaignName, DISABLED)
                .campaignNavigationMenu.clickLaunchButton()
                .launchCampaign();
        Log.logRecord("Campaign <" + campaignName + "> is reactivated");
    }



    /*Scenarios to open Multi-Campaign Editor page for some campaign.
     * Precondition: Page with header should opened. Campaign with @campaignName should exist with defined @status.
     * 1. Navigate to Campaigns page
     * 2. Search campaign by @campaignName and @status
     * 3. Select campaign
     * 4. Navigate to Editor page.
     * 5. Select view by @pageViewName
     * 6. Select localization type by @contentType (COPY, IMAGE, CONFIGURATION or COLOR)
     * 7. Find localization by @localizationName and click 'Copy to Other Campaigns' button
     * @Returns: Multi-Campaign Editor page for mentioned parameters.
     * */
    public static PageMultiCampaignEditor openMultiCampaignEditorPage(String campaignName,
                                                                      Table.Status status,
                                                                      String pageViewName,
                                                                      String localizationName,
                                                                      EditorPage.LocalizationType contentType) {
        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(campaignName, status);
        EditorPage editor = detailsPage.campaignNavigationMenu.openEditorPage();
        editor = editor.switchViewByName(pageViewName);
        editor.switchTo(contentType);
        PageMultiCampaignEditor mceEditor =  editor.clickCopyToOtherCampaigns(contentType, localizationName + "#");
        Log.logRecord("Multi-Campaign Editor page is opened for campaign = <" + campaignName + ">, localization = <" + contentType + " --> " + localizationName + ">.");
        return mceEditor;
    }

    public static String getCampaignStatusFromMenu(){
        return new CampaignNavigationMenu().getCampaignStatus();
    }




    public static void verifyPagination(Pagination pagination) {
        int page = 1;
        String current = pagination.getCurrentPage();
        Assert.assertEquals(current, String.valueOf(page), "FAILED: Pagination: First page is not opened");

        pagination.next();
        page++;
        current = pagination.getCurrentPage();
        Assert.assertEquals(current, String.valueOf(page), "FAILED: Pagination: Incorrect page value after next() method");

        pagination.first();
        page = 1;
        current = pagination.getCurrentPage();
        Assert.assertEquals(current, String.valueOf(page), "FAILED: Pagination: Incorrect page value after first() method");

        pagination.last();
        current = pagination.getCurrentPage();

        pagination.previous();
        Assert.assertNotEquals(pagination.getCurrentPage(), current, "FAILED: Pagination: page is not switched after previous() method");

        pagination.last();
        pagination.first();
        page = 1;
        current = pagination.getCurrentPage();
        Assert.assertEquals(current, String.valueOf(page), "FAILED: Pagination: Incorrect page value after first() method");

        Log.testPassed("Pagination successfully verified");
    }

    public static SiteSettingsBasicTab openSiteSettingsPage(){
        SiteSettingsBasicTab page = new Header().openMenu().clickSiteSettings();
        Log.logRecord("Site Settings page is opened (Basic Tab)");
        return page;
    }

    public static Site getSiteIntegrationValues(){
        SiteSettingsBasicTab basicTab = openSiteSettingsPage();
        String siteID = basicTab.getSiteID();
        String apiKey = basicTab
                .openIntegrationSettingsTab()
                .getApiKey();
        return new Site().setData(siteID, apiKey);
    }



}
