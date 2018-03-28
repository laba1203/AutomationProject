package execution.editor.mce;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.CampaignType;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.campaignsPage.Table;
import talkable.talkableSite.headerFrame.Header;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.*;
import static talkable.talkableSite.campaignsPage.Table.Status.TEST;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1NlY_NBmvKIRjmqb2d7oQPuDZEs6s7fToZkrSJPhwOaY
 * */
public class MultiCampaignEditorTesting extends BaseTest {

    private static final String siteName = PropertyLoader.loadProperty("sites.name.multiCampaignEditorTesting");

    //name of campaigns used in test:
    private String campaignNameFW_1;
    private String campaignNameSA;
    private String campaignNameFW_2;
    private String campaignNamePP;

    //Variables for each test set (default values for COPY tests):
    private String newContentValue = "New Copy Value";
    private String localizationName = "Advocate trigger cta";
    private String campaignView = "Advocate trigger widget";

    private PageMultiCampaignEditor mcePage;

    @BeforeSuite
    public void precondition() {
        //login to Talkable and select site
        CommonScenarios.login(EnvFactory.getUser(), EnvFactory.getPassword())
                .selectByVisibleText(siteName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //clear data from previous execution:
        new Header().openCampaignsPage().deleteAllTestCampaigns();
        //Create campaigns for testing:
        campaignNameSA = CommonScenarios.initiateCampaignCreation(CampaignType.Invite, CampaignPlacement.Standalone)
                .campaignNavigationMenu.getCampaignName();
        campaignNameFW_1 = CommonScenarios.initiateCampaignCreation(CampaignType.Invite, CampaignPlacement.FloatingWidget)
                .campaignNavigationMenu.getCampaignName();
        campaignNameFW_2 = CommonScenarios.initiateCampaignCreation(CampaignType.AdvocateDashboard, CampaignPlacement.FloatingWidget)
                .campaignNavigationMenu.getCampaignName();
        campaignNamePP = CommonScenarios.initiateCampaignCreation(CampaignType.Invite, CampaignPlacement.PostPurchase)
                .campaignNavigationMenu.getCampaignName();

    }


    /*
     *****  Verification of MCE Page for COPY localization type *******
     */
    @BeforeGroups("copyUpdate")
    public void setTestData() {
        driver.navigate().to(EnvFactory.getAdminUrl());
        new Header();
        //set values:
        newContentValue = "New Copy Value";
        localizationName = "Advocate trigger cta";
        campaignView = "Advocate trigger widget";
    }

    // Open FW campaign.
    // Navigate to Simple Editor --> Copy (AD Trigger Widget view)
    // For attribute "Advocate trigger cta" click 'Copy to other campaign' button
    @Test(groups = "copyUpdate")
    public void test11_openMultiCampaignEditorCOPY() {
        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(campaignNameFW_1, TEST);
        EditorPage editor = detailsPage.campaignNavigationMenu.openEditorPage();
        mcePage = editor.clickCopyToOtherCampaigns(COPY, localizationName + "#");

        // Verify Campaign view field on MCE screen
        Assert.assertEquals(mcePage.getCampaignViewValue(), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(mcePage.getContentValue(), localizationName);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "1", "FAILED: Incorrect count of Selected campaigns");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "1", "FAILED: Incorrect count of Unselected campaigns");
        // Verify ineligible campaign list
        Assert.assertEquals(mcePage.getIneligibleCampaigns().getCampaignCount(), "2", "FAILED: Incorrect count of Ineligible campaigns");
    }

    //Select second FW campaign
    @Test(groups = "copyUpdate")
    public void test12_selectSecondFWCampaignCOPY() {
        mcePage.selectCampaign(campaignNameFW_2);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "2", "FAILED: Incorrect count of Selected campaigns");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "0", "FAILED: Incorrect count of Unselected campaigns");
    }

    //Enter new value and click Save localizationName button
    @Test(groups = "copyUpdate")
    public void test13_updateValueCOPY() {
        mcePage = mcePage.updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue, "FAILED: Incorrect new content value");
    }

    @Test(groups = "copyUpdate")
    public void test14_checkValueInFirstCampaignCOPY() {
        EditorPage editor = mcePage.backToEditor();
        String value = editor.getLocalizationValue(COPY, localizationName + "#");
        Assert.assertEquals(value, newContentValue, "FAILED: Incorrect new content value");
    }

    @Test(groups = "copyUpdate")
    public void test15_checkValueInSecondCampaignCOPY() {
        //open campaignDetailsPage and open second campaign:
        PageCampaigns campaignsPage = new EditorPage().campaignNavigationMenu
                .openDetailsPage().header.openCampaignsPage();
        EditorPage editor = campaignsPage.openCampaignByName(campaignNameFW_2, TEST).campaignNavigationMenu.openEditorPage();
        // Verify value in Editor:
        String value = editor.getLocalizationValue(COPY, localizationName + "#");
        Assert.assertEquals(value, newContentValue, "FAILED: Incorrect new content value");
    }


    /*
     *****  Verification of MCE Page for COLOR localization type *******

     */
    @BeforeGroups("colorUpdate")
    public void setUpForColorTests() {
        driver.navigate().to(EnvFactory.getAdminUrl());
        new Header();
        //set test data
        localizationName = "Advocate share page email button background";
        campaignView = "Advocate share page";
        newContentValue = "#23f908";
    }

    // Open PP campaign.
    // Navigate to Simple Editor --> Color (PP Share page view)
    // For attribute "Advocate share page email button background" click 'Copy to other campaign' button
    @Test(groups = "colorUpdate")
    public void test21_openMultiCampaignEditorCOLOR() {
        mcePage = openMultiCampaignEditorAndVerifyCampaignsCount(campaignNamePP,
                TEST,
                campaignView,
                localizationName,
                COLOR,
                "1",
                "3",
                "0");
    }

    //Select campaign FW and SA campaign
    @Test(groups = "colorUpdate")
    public void test22_selectCampaignsCOLOR() {
        mcePage.selectCampaign(campaignNameFW_1);
        mcePage.selectCampaign(campaignNameSA);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "3");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "1");
    }

    // Enter new value and click Save content button
    @Test(groups = "colorUpdate")
    public void test23_updateValueCOLOR() {
        mcePage = mcePage.updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue);
    }

    @Test(groups = "colorUpdate")
    public void test24_checkValueInFirstCampaignCOLOR() {

        checkValueInFirstCampaign(mcePage,
                COLOR,
                localizationName,
                newContentValue);
    }

    @Test(groups = "copyUpdate")
    public void test25_checkValueInSACampaignCOLOR() {
        checkValuesInOtherCampaign(new EditorPage(),
                campaignNameSA,
                TEST,
                campaignView,
                COLOR,
                localizationName,
                newContentValue);
    }

    @Test(groups = "copyUpdate")
    public void test26_checkValueInFWCampaignCOLOR() {
        checkValuesInOtherCampaign(new EditorPage(),
                campaignNameFW_1,
                TEST,
                campaignView,
                COLOR,
                localizationName,
                newContentValue);
    }

    /*
     *****  Verification of MCE Page for CONFIGURATION localization type *******
     */

    @BeforeGroups("configurationUpdate")
    public void setUpForConfigurationTests() {

        System.out.println("DEBAG: CONFIGURATION UPDATE Befor group executed");

        driver.navigate().to(EnvFactory.getAdminUrl());
        System.out.println("--   DEBAG: Admin page is opened CONFIG ---");
        new Header();
        //set test data
        localizationName = "Automatic font sizing";
        campaignView = "Friend claim page";
        newContentValue = "Disabled";
    }

//    Open SA campaign.
//    Navigate to Simple Editor --> Configuration (Friend claim page view)
//    For attribute "Automatic font sizing" click 'Copy to other campaign' button
    @Test(groups = "configurationUpdate")
    public void test31_openMultiCampaignEditorCONFIG() {

        System.out.println("DEBAG: test31_openMultiCampaignEditorCONFIG START");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mcePage = openMultiCampaignEditorAndVerifyCampaignsCount(campaignNameSA,
                TEST,
                campaignView,
                localizationName,
                CONFIGURATION,
                "1",
                "3",
                "0");
    }

    //Select PP campaign
    @Test(groups = "configurationUpdate", dependsOnMethods = "test31_openMultiCampaignEditorCONFIG")
    public void test32_selectCampaignsCONFIG() {

        System.out.println("DEBAG: test32_selectCampaignsCONFIG START");

        mcePage.selectCampaign(campaignNamePP);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "2");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "2");
    }

    // Enter new value and click Save content button
    @Test(groups = "configurationUpdate", dependsOnMethods = "test32_selectCampaignsCONFIG")
    public void test33_updateValueCONFIG() {
        mcePage = mcePage.updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue);
    }

    @Test(groups = "configurationUpdate", dependsOnMethods = "test33_updateValueCONFIG")
    public void test34_checkValueInFirstCampaignCONFIG() {
        checkValueInFirstCampaign(mcePage,
                CONFIGURATION,
                localizationName,
                newContentValue);
    }

    @Test(groups = "configurationUpdate", dependsOnMethods = "test34_checkValueInFirstCampaignCONFIG")
    public void test35_checkValueInSACampaignCONFIG() {
        checkValuesInOtherCampaign(new EditorPage(),
                campaignNameSA,
                TEST,
                campaignView,
                CONFIGURATION,
                localizationName,
                newContentValue);
    }

    /*
     *****  Verification of MCE Page for IMAGES localization type *******
     */

    @BeforeGroups("imageUpdate")
    public void setUpForImageTests() {
        driver.navigate().to(EnvFactory.getAdminUrl());
        System.out.println("--   DEBAG: Admin page is opened  ---");
        new Header();
        //set test data
        localizationName = "Advocate signup page background";
        campaignView = "Advocate signup page";
        newContentValue = "tkbl_default_icon-link-color-2x.png";
    }

    @Test(groups = "imageUpdate", dependsOnGroups = "configurationUpdate", alwaysRun = true)
    public void test41_openMultiCampaignEditorIMAGES() {
        mcePage = openMultiCampaignEditorAndVerifyCampaignsCount(campaignNameSA,
                TEST,
                campaignView,
                localizationName,
                IMAGES,
                "1",
                "1",
                "2");
    }

    @Test(groups = "imageUpdate", dependsOnMethods = "test41_openMultiCampaignEditorIMAGES")
    public void test42_selectCampaignsIMAGES() {
        mcePage.selectCampaign(campaignNameFW_1);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "2", "FAILED: Incorrect count of Selected campaigns");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "0", "FAILED: Incorrect count of Unselected campaigns");
    }

    @Test(groups = "imageUpdate", dependsOnMethods = "test42_selectCampaignsIMAGES")
    public void test43_updateValueIMAGES() {
        mcePage = mcePage.updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue, "FAILED: Incorrect New Content Value");
    }

    @Test(groups = "imageUpdate", dependsOnMethods = "test43_updateValueIMAGES")
    public void test44_checkValueInFirstCampaignIMAGES() {
        checkValueInFirstCampaign(mcePage,
                IMAGES,
                localizationName,
                newContentValue);
    }

    @Test(groups = "imageUpdate", dependsOnMethods = "test44_checkValueInFirstCampaignIMAGES")
    public void test45_checkValueInFWCampaignIMAGES() {
        checkValuesInOtherCampaign(new EditorPage(),
                campaignNameFW_1,
                TEST,
                campaignView,
                IMAGES,
                localizationName,
                newContentValue);
    }



    @AfterSuite
    public void deleteTestCampaigns() {
        CampaignDetailsPage detailsPage = new EditorPage().campaignNavigationMenu.openDetailsPage();
        detailsPage.header.openCampaignsPage().deleteAllTestCampaigns();
    }



    /*
    General Scenarios for verifications of MCE page:
    * */
    private PageMultiCampaignEditor openMultiCampaignEditorAndVerifyCampaignsCount(String campaignName,
                                                                Table.Status status,
                                                                String campaignView,
                                                                String localizationName,
                                                                EditorPage.LocalizationMode localizationType,
                                                                String expectedSelectedCampaignsCount,
                                                                String expectedUnselectedCampaignsCount,
                                                                String expectedIneligibleCampaignsCount)
    {
        PageMultiCampaignEditor mcePage = CommonScenarios.openMultiCampaignEditorPage(
                campaignName,
                status,
                campaignView,
                localizationName,
                localizationType);
        // Verify Campaign view field on MCE screen
        Assert.assertEquals(mcePage.getCampaignViewValue(), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(mcePage.getContentValue(), localizationName);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), expectedSelectedCampaignsCount, "FAILED: Incorrect count of Selected campaigns");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), expectedUnselectedCampaignsCount, "FAILED: Incorrect count of Unselected campaigns");
        // Verify ineligible campaign list
        Assert.assertEquals(mcePage.getIneligibleCampaigns().getCampaignCount(), expectedIneligibleCampaignsCount, "FAILED: Incorrect count of Ineligible campaigns");

        return mcePage;
    }

    private void checkValueInFirstCampaign(PageMultiCampaignEditor mcePage,
                                             EditorPage.LocalizationMode localizationType,
                                             String localizationName,
                                             String newContentValue){
        EditorPage editor = mcePage.backToEditor();
        editor.switchTo(localizationType);
        String value = editor.getLocalizationValue(localizationType, localizationName + "#");
        Assert.assertEquals(value, newContentValue, "FAILED: Incorrect New Content Value");
    }

    private void checkValuesInOtherCampaign(EditorPage editorPage,
                                            String campaignName,
                                            Table.Status campaignStatus,
                                            String campaignViewName,
                                            EditorPage.LocalizationMode mode,
                                            String localizationName,
                                            String newContentValue){
        //open campaignDetailsPage and open second campaign:
        PageCampaigns campaignsPage = editorPage.campaignNavigationMenu
                .openDetailsPage().header.openCampaignsPage();
        EditorPage editor = campaignsPage.openCampaignByName(campaignName, campaignStatus).
                campaignNavigationMenu.openEditorPage();
        //Switch view:
        editor = editor.switchViewByName(campaignViewName);
        //Switch content
        editor.switchTo(mode);
        // Verify value in Editor:
        String value = editor.getLocalizationValue(mode, localizationName + "#");
        Assert.assertEquals(value, newContentValue, "FAILED: Incorrect New Content Value");
    }

}
