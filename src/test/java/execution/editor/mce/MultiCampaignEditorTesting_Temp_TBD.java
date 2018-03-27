package execution.editor.mce;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
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
public class MultiCampaignEditorTesting_Temp_TBD extends BaseTest {

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

    @BeforeClass
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
        newContentValue = "New Copy Value";
        localizationName = "Advocate trigger cta";
        campaignView = "Advocate trigger widget";
    }

    // Open FW campaign.
    // Navigate to Simple Editor --> Copy (AD Trigger Widget view)
    // For attribute "Advocate trigger cta" click 'Copy to other campaign' button
    @Test(groups = "copyUpdate")
    public void test11_openMultiCampaignEditor() {
        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(campaignNameFW_1, TEST);
        EditorPage editor = detailsPage.campaignNavigationMenu.openEditorPage();
        mcePage = editor.clickCopyToOtherCampaigns(COPY, localizationName + "#");

        // Verify Campaign view field on MCE screen
        Assert.assertEquals(mcePage.getCampaignViewValue(), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(mcePage.getContentValue(), localizationName);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "1");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "1");
        // Verify ineligible campaign list
        Assert.assertEquals(mcePage.getIneligibleCampaigns().getCampaignCount(), "2");
    }

    //Select second FW campaign
    @Test(groups = "copyUpdate")
    public void test12_selectSecondFWCampaign() {
        mcePage.selectCampaign(campaignNameFW_2);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "2");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "0");
    }

    //Enter new value and click Save localizationName button
    @Test(groups = "copyUpdate")
    public void test13_updateValue() {
        mcePage = mcePage.updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue);
    }

    @Test(groups = "copyUpdate")
    public void test14_checkValueInFirstCampaign() {
        EditorPage editor = mcePage.backToEditor();
        String value = editor.getLocalizationValue(COPY, localizationName + "#");
        Assert.assertEquals(value, newContentValue);
    }

    @Test(groups = "copyUpdate")
    public void test15_checkValueInSecondCampaign() {
        //open campaignDetailsPage and open second campaign:
        PageCampaigns campaignsPage = new EditorPage().campaignNavigationMenu
                .openDetailsPage().header.openCampaignsPage();
        EditorPage editor = campaignsPage.openCampaignByName(campaignNameFW_2, TEST).campaignNavigationMenu.openEditorPage();
        // Verify value in Editor:
        String value = editor.getLocalizationValue(COPY, localizationName + "#");
        Assert.assertEquals(value, newContentValue);
    }



    private void repeatTestForColor(String campaignName,
                                    String viewName,
                                    String localizationName,
                                    EditorPage.LocalizationMode mode,
                                    String selectedCampaignsAfterUpdate,
                                    String unselectedCampaignsAfterUpdate
                                    ){
        mcePage = openMultiCampaignEditorAndVerifyCampaignsCount(campaignNamePP,
                TEST,
                campaignView,
                localizationName,
                COLOR,
                "1",
                "3",
                "0");
        mcePage.selectCampaign(campaignNameFW_1);
        mcePage.selectCampaign(campaignNameSA);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "3");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "1");

        mcePage = mcePage.updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue);

        checkValueInFirstCampaign(mcePage,
                COLOR,
                localizationName,
                newContentValue);
        checkValuesInOtherCampaign(new EditorPage(),
                campaignNameSA,
                TEST,
                campaignView,
                COLOR,
                localizationName,
                newContentValue);
        checkValuesInOtherCampaign(new EditorPage(),
                campaignNameFW_1,
                TEST,
                campaignView,
                COLOR,
                localizationName,
                newContentValue);
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
    public void test21_openMultiCampaignEditor() {
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
    public void test22_selectCampaigns() {
        mcePage.selectCampaign(campaignNameFW_1);
        mcePage.selectCampaign(campaignNameSA);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "3");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "1");
    }

    // Enter new value and click Save content button
    @Test(groups = "colorUpdate")
    public void test23_updateValue() {
        mcePage = mcePage.updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue);
    }

    @Test(groups = "colorUpdate")
    public void test24_checkValueInFirstCampaign() {

        checkValueInFirstCampaign(mcePage,
                COLOR,
                localizationName,
                newContentValue);
    }

    @Test(groups = "copyUpdate")
    public void test25_checkValueInSACampaign() {
        checkValuesInOtherCampaign(new EditorPage(),
                campaignNameSA,
                TEST,
                campaignView,
                COLOR,
                localizationName,
                newContentValue);
    }

    @Test(groups = "copyUpdate")
    public void test26_checkValueInFWCampaign() {
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
        driver.navigate().to(EnvFactory.getAdminUrl());
        new Header();
        //set test data
        localizationName = "Automatic font sizingd";
        campaignView = "Friend claim page";
        newContentValue = "Disabled";
    }

//    Open SA campaign.
//    Navigate to Simple Editor --> Configuration (Friend claim page view)
//    For attribute "Automatic font sizing" click 'Copy to other campaign' button
    @Test(groups = "configurationUpdate")
    public void test31_openMultiCampaignEditor() {
        mcePage = openMultiCampaignEditorAndVerifyCampaignsCount(campaignNamePP,
                TEST,
                campaignView,
                localizationName,
                CONFIGURATION,
                "1",
                "3",
                "0");
    }

    //Select campaign FW and SA campaign
    @Test(groups = "configurationUpdate")
    public void test32_selectCampaigns() {
        mcePage.selectCampaign(campaignNamePP);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "2");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "2");
    }

    // Enter new value and click Save content button
    @Test(groups = "configurationUpdate")
    public void test33_updateValue() {
        mcePage = mcePage.updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue);
    }

    @Test(groups = "configurationUpdate")
    public void test34_checkValueInFirstCampaign() {
        checkValueInFirstCampaign(mcePage,
                CONFIGURATION,
                localizationName,
                newContentValue);
    }

    @Test(groups = "copyUpdate")
    public void test35_checkValueInSACampaign() {
        checkValuesInOtherCampaign(new EditorPage(),
                campaignNameSA,
                TEST,
                campaignView,
                CONFIGURATION,
                localizationName,
                newContentValue);
    }



    @AfterClass
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
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), expectedSelectedCampaignsCount);
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), expectedUnselectedCampaignsCount);
        // Verify ineligible campaign list
        Assert.assertEquals(mcePage.getIneligibleCampaigns().getCampaignCount(), expectedIneligibleCampaignsCount);

        return mcePage;
    }

    private void checkValueInFirstCampaign(PageMultiCampaignEditor mcePage,
                                             EditorPage.LocalizationMode localizationType,
                                             String localizationName,
                                             String newContentValue){
        EditorPage editor = mcePage.backToEditor();
        editor.switchTo(localizationType);
        String value = editor.getLocalizationValue(localizationType, localizationName + "#");
        Assert.assertEquals(value, newContentValue);
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
        Assert.assertEquals(value, newContentValue);
    }

}
