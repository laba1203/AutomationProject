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
import talkable.talkableSite.fraud.settings.FraudSettingsPage;
import talkable.talkableSite.fraud.settings.SectionRulesForAdvocate;
import talkable.talkableSite.fraud.settings.SectionRulesForFriend;
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
import util.logging.Log;

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
//        homePage.loginButton.click();
//        LoginPage loginPage = new LoginPage();
        LoginPage loginPage = homePage.clickLoginButton();
        return loginPage.submitLoginForm(email, password);
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
        Assert.assertEquals(integrationInstructionPage.header.getSiteName(), siteName);
        return integrationInstructionPage;
    }

    public static SiteDashboardPage switchToSiteByVisibleText(String siteName) {
        new Header().selectByVisibleText(siteName);
        SiteDashboardPage siteDashboardPage = null;
        try {
            siteDashboardPage = new SiteDashboardPage();
        }catch (AssertionError e) {

            //TODO: This part should be removed when the related defect is fixed. https://talkable.atlassian.net/browse/PR-9056

            System.out.println("DEBAG: Dashboard is not opened.");
            Assert.assertEquals(new IntegrationInstructionPage().header.getSiteName(), siteName, "FAILED: Site is not switched");
            System.out.println("DEBAG: Integration Instruction page is opened instead of Site Dashboard");
        }
        if(siteDashboardPage != null) {
            Assert.assertEquals(siteDashboardPage.header.getSiteName(), siteName, "FAILED: Site is not switched");
        }
        Log.siteSwitchedMsg(siteName);
        return siteDashboardPage;
    }

    public static PageCampaigns openCampaignsPage() {
        return new Header().openCampaignsPage();
    }

    public static PageCampaigns deactivateAllCampaigns() {
        return openCampaignsPage().deactivateAllLiveCampaigns();
    }

    public static PageCampaigns deleteAllCampaignsWithStatus(Table.Status status){
        return new Header().openCampaignsPage().deleteAllCampaignsWithStatus(status);
    }

    public static PageCampaignRules openCampaignRulesPage() {
        return new CampaignNavigationMenu().openRulesPage();
    }

    public static PageCampaignRules setCampaignNameOnRulesPage(String campaignName) {
        PageCampaignRules page = new PageCampaignRules().setCampaignName(campaignName);
        Assert.assertEquals(page.getCampaignName(), campaignName, "FAILED: Campaign name is not updated on Rules page");
        return page;
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
        return campaignDetailsPage;
    }

    /***
     * Scenario to initiate campaign creation from Campaigns Page..
     * Precondition: Header should be available.
     * Post-condition: Campaign Details page of newly created campaign(as per input parameters)
     * */
    public static CampaignDetailsPage initiateCampaignCreationFromCampaignsPage(CampaignType campaignType, CampaignPlacement placementType) {
        return new Header().openCampaignsPage().createNewCampaign(campaignType, placementType);
    }


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
        return new CampaignDetailsPage();
    }

    /***
     * Scenario to launch campaign from any page with CampaignNavigation menu
     * Post-condition: Details page of launched campaign.
     * */
    public static CampaignDetailsPage createAndLaunchCampaign(CampaignType campaignType, CampaignPlacement placementType) {
        initiateCampaignCreationFromCampaignsPage(campaignType, placementType);
        try {
            // This part is added due to an existing defect. Error 500 is returned when campaign is launched directly after creation.
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return launchCampaign();
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
        Assert.assertEquals(integrationInstructionPage.header.getSiteName(), siteName);
        Log.userAndSiteCreatedMsg(email, siteName);

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
        return createNewPurchasePage.createOfferAndSwitchToCampaign();
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

        return newAffiliateMember.createMemberAndSwitchToCampaign(newAffiliatedMemberEmail);
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

        return new CampaignDetailsPage();
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
                                                                      EditorPage.LocalizationMode contentType) {
        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(campaignName, status);
        EditorPage editor = detailsPage.campaignNavigationMenu.openEditorPage();
        editor = editor.switchViewByName(pageViewName);
        editor.switchTo(contentType);
        return editor.clickCopyToOtherCampaigns(contentType, localizationName + "#");
    }


    /*Scenarios to add Campaign Placement(inclusion or exclusion).
     * Precondition: Header should be available.
     * 1. Open Campaign Placement page
     * 2. Add inclusion oe exclusion (as per @isInclusion)
     * 3. wait till changes applied
     * @Returns: PageCampaignPlacements for mentioned parameters.
     * */
    public static PageCampaignPlacements addCampaignPlacement(CampaignPlacement placement,
                                                              boolean isInclusion,
                                                              boolean regex,
                                                              String placementText) {
        PageCampaignPlacements campaignPlacements = new Header().openMenu().clickCampaignPlacementsButton();
        if (isInclusion) {
            campaignPlacements = campaignPlacements.addInclusion(placement, regex, placementText);
        } else {
            campaignPlacements = campaignPlacements.addExclusion(placement, regex, placementText);
        }
        return campaignPlacements.waitTillChangesApplied();
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
    }

    public static SiteSettingsBasicTab openSiteSettingsPage(){
        return new Header().openMenu().clickSiteSettings();
    }

    public static Site getSiteIntegrationValues(){
        SiteSettingsBasicTab basicTab = openSiteSettingsPage();
        String siteID = basicTab.getSiteID();
        String apiKey = basicTab
                .openIntegrationSettingsTab()
                .getApiKey();
        return new Site().setData(siteID, apiKey);
    }

    /* Scenarios for Fraud rules */
    public static FraudSettingsPage openFraudSettings() {
        return new Header().openMenu().clickFraudSettingsButton();
    }

    public static FraudSettingsPage setFraudSettingsProfile(FraudSettingsPage.ProfileType profileType) {
        return new FraudSettingsPage().setProfile(profileType);
    }

    private static String getFraudSettingsProfileDescription() {
        return new FraudSettingsPage().getProfileDescription();
    }

    private static String getFraudSettingsProfileName() {
        return new FraudSettingsPage().getProfileName();
    }

    public static void setFraudProfileAndAssertProfileNameAndDescription(FraudSettingsPage.ProfileType profileType,
                                                                         String profileName,
                                                                         String profileDescription) {
        openFraudSettings();
        setFraudSettingsProfile(profileType);
        Assert.assertEquals(
                getFraudSettingsProfileName(),
                profileName,
                "FAILED: Incorrect fraud settings profile name"
        );
        Assert.assertEquals(
                getFraudSettingsProfileDescription(),
                profileDescription,
                "FAILED: Incorrect fraud settings profile description"
        );
    }

    public static SectionRulesForAdvocate getAdvocateRuleSectionFromFraudSetting() {
        return new FraudSettingsPage().getRulesForAdvocateSection();
    }

    public static SectionRulesForFriend getFriendRulesSectionFromFraudSetting() {
        return new FraudSettingsPage().getRulesForFriend();
    }

    public static void assertValuesInRulesForAdvocateSection(String expectedMatchingEmailOrCookieValue,
                                                             String expectedSimilarEmailMatch,
                                                             String expectedMatchingShippingAddress,
                                                             String expectedIpAddressAndUserAgentValue,
                                                             String expectedIpAddressOnlyValue,
                                                             String expectedCrossReferralValue
    ) {
        String selectedOption = CommonScenarios.getAdvocateRuleSectionFromFraudSetting().getMatchingEmailOrCookieValue();
        Assert.assertEquals(selectedOption, expectedMatchingEmailOrCookieValue, "FAILED: Incorrect selected value in 'Matching Email or Cookie on Friend Purchase' in Advocate Rules.");

        selectedOption = CommonScenarios.getAdvocateRuleSectionFromFraudSetting().getSimilarEmailMatch();
        Assert.assertEquals(selectedOption, expectedSimilarEmailMatch, "FAILED: Incorrect selected value in 'Similar Email Match' in Advocate Rules.");

        selectedOption = CommonScenarios.getAdvocateRuleSectionFromFraudSetting().getMatchingShippingAddress();
        Assert.assertEquals(selectedOption, expectedMatchingShippingAddress, "FAILED: Incorrect selected value in 'Similar Email Match' in Advocate Rules.");

        selectedOption = CommonScenarios.getAdvocateRuleSectionFromFraudSetting().getMatchingIpAddressAndUserAgent();
        Assert.assertEquals(selectedOption, expectedIpAddressAndUserAgentValue, "FAILED: Incorrect selected value in 'Matching by Combination of IP Address & User Agent' in Advocate Rules.");

        selectedOption = CommonScenarios.getAdvocateRuleSectionFromFraudSetting().getIpAddressOnly();
        Assert.assertEquals(selectedOption, expectedIpAddressOnlyValue, "FAILED: Incorrect selected value in 'Matching by IP Address only' in Advocate Rules.");

        selectedOption = CommonScenarios.getAdvocateRuleSectionFromFraudSetting().getFriendAndAdvocateReferEachOther();
        Assert.assertEquals(selectedOption, expectedCrossReferralValue, "FAILED: Incorrect selected value in 'Friend and Advocate Refer Each Other' in Advocate Rules.");

    }

    public static void assertValuesInRulesForFriendSection(String expectedMatchingCookieValue,
                                                           String expectedIpAddressAndUserAgentValue,
                                                           String expectedIpAddressOnlyValue,
                                                           String expectedSimilarEmailMatch,
                                                           String expectedCrossReferralValue
    ) {
        String selectedOption = CommonScenarios.getFriendRulesSectionFromFraudSetting().getMatchingCookieValue();
        Assert.assertEquals(selectedOption, expectedMatchingCookieValue, "FAILED: Incorrect selected value in 'Matching Cookie on Friend Claim Page' in Rules for Friend section");

        selectedOption = CommonScenarios.getFriendRulesSectionFromFraudSetting().getMatchingIpAddressAndUserAgent();
        Assert.assertEquals(selectedOption, expectedIpAddressAndUserAgentValue, "FAILED: Incorrect selected value in 'Matching by Combination of IP Address & User Agent' in Rules for Friend section");

        selectedOption = CommonScenarios.getFriendRulesSectionFromFraudSetting().getIpAddressOnly();
        Assert.assertEquals(selectedOption, expectedIpAddressOnlyValue, "FAILED: Incorrect selected value in 'Matching by IP Address only' in Rules for Friend section");

        selectedOption = CommonScenarios.getFriendRulesSectionFromFraudSetting().getSimilarEmailMatch();
        Assert.assertEquals(selectedOption, expectedSimilarEmailMatch, "FAILED: Incorrect selected value in 'Similar Email Match' in Rules for Friend section");

        selectedOption = CommonScenarios.getFriendRulesSectionFromFraudSetting().getFriendAndAdvocateReferEachOther();
        Assert.assertEquals(selectedOption, expectedCrossReferralValue, "FAILED: Incorrect selected value in 'Friend and Advocate Refer Each Other' in Rules for Friend section");

    }

    public static FraudSettingsPage setReferralApprovalModeOnFraudSetting(FraudSettingsPage.ApprovalMode mode){
        return new FraudSettingsPage().setApprovalMode(mode);
    }




}
