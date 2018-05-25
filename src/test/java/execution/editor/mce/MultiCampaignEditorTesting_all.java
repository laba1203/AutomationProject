package execution.editor.mce;

import common.cases.CommonScenarios;
import common.cases.functionalities.MceScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.common.CampaignPlacement.*;
import static talkable.common.CampaignType.AdvocateDashboard;
import static talkable.common.CampaignType.Invite;
import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationType.*;
import static talkable.talkableSite.campaignsPage.Table.Status.*;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1NlY_NBmvKIRjmqb2d7oQPuDZEs6s7fToZkrSJPhwOaY
 * */
public class MultiCampaignEditorTesting_all extends BaseTest {

    private static final String siteName = PropertyLoader.loadProperty("sites.name.multiCampaignEditorTesting");

    //name of campaigns used in test:
    private String campaignNameFW_1;
    private String campaignNameSA;
    private String campaignNameFW_2;
    private String campaignNamePP;

    @Test(groups = "updateContent")
    public void precondition() {
        //login to Talkable and select site
        CommonScenarios.login(EnvFactory.getCommonUser(), EnvFactory.getPassword()).openCampaignsPage();
        CommonScenarios.switchToNonIntegratedSiteByVisibleText(siteName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //clear data from previous execution:
        CommonScenarios.openCampaignsPage();
        CommonScenarios.deleteAllCampaignsWithStatus(TEST);
        CommonScenarios.deleteAllCampaignsWithStatus(LIVE);
        CommonScenarios.deleteAllCampaignsWithStatus(DISABLED);

        //Create campaigns for testing:
        campaignNameSA = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, Standalone);
        CommonScenarios.openCampaignsPage();


        campaignNameFW_1 = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, FloatingWidget);
        CommonScenarios.openCampaignsPage();

        campaignNameFW_2 = CommonScenarios.createNewCampaignFromCampaignsPage(AdvocateDashboard, FloatingWidget);
        CommonScenarios.openCampaignsPage();

        campaignNamePP = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, PostPurchase);

    }


    @Test(groups = "updateContent", dependsOnMethods = "precondition")
    public void modifyCOPY() {
        //test data:
        EditorPage.LocalizationType mode = COPY;
        String newContentValue = "New Copy Value";
        String localizationName = "Advocate trigger cta";
        String campaignView = "Advocate trigger widget";

        CommonScenarios.navigateToAdminUrl();

        MceScenarios.openMultiCampaignEditor(campaignNameFW_1, TEST, campaignView, localizationName, mode);

        // Verify Campaign view field on MCE screen
        Assert.assertEquals(MceScenarios.getCampaignViewValue(mode), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(MceScenarios.getContentValue(mode), localizationName);


        //Verify campaigns count in Selected, Unselected and Ineligible sections
        MceScenarios.assertCampaignsCountInGrids(mode,
                "1",
                "1",
                "2");

        //select second campaign:
        MceScenarios.selectCampaignOnMceScreen(mode, campaignNameFW_2);

        //verify Selected and Unselected campaigns count
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(mode, "2", "0");

        //update content
        MceScenarios.updateContentValue(mode, newContentValue);

        //check new content value in the First Campaign
        MceScenarios.returnToSimpleEditorAndAssertContentValue(mode, localizationName, newContentValue);

        //checkValueInSecondCampaignCOPY
        MceScenarios.assertContentValueInSimpleEditor(campaignNameFW_2, TEST, campaignView, mode, localizationName, newContentValue);
    }


    /*
     *****  Verification of MCE Page for COLOR localization type *******

     */
    @Test(groups = "updateContent", dependsOnMethods = "precondition")
    public void updateColor() {
        //test data:
        EditorPage.LocalizationType localizationType = COLOR;
        String newContentValue = "#23f908";
        String localizationName = "Advocate share page email button background";
        String campaignView = "Advocate share page";

        CommonScenarios.navigateToAdminUrl();

        MceScenarios.openMultiCampaignEditor(campaignNamePP, TEST, campaignView, localizationName, localizationType);

        // Verify Campaign view field on MCE screen
        Assert.assertEquals(MceScenarios.getCampaignViewValue(localizationType), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(MceScenarios.getContentValue(localizationType), localizationName);

        //Verify campaigns count in Selected, Unselected and Ineligible sections
        MceScenarios.assertCampaignsCountInGrids(localizationType,
                "1",
                "3",
                "0");

        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNameFW_1);
        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNameSA);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(localizationType, "3", "1");

        MceScenarios.updateContentValue(localizationType, newContentValue);

        MceScenarios.returnToSimpleEditorAndAssertContentValue(localizationType, localizationName, newContentValue);
        MceScenarios.assertContentValueInSimpleEditor(campaignNameSA, TEST, campaignView, localizationType, localizationName, newContentValue);
        MceScenarios.assertContentValueInSimpleEditor(campaignNameFW_1, TEST, campaignView, localizationType, localizationName, newContentValue);

    }


    /*
     *****  Verification of MCE Page for CONFIGURATION localization type *******
     */
    @Test(groups = "updateContent", dependsOnMethods = "precondition")
    public void updateCONFIG() {
        //test data:
        EditorPage.LocalizationType localizationType = CONFIGURATION;
        String localizationName = "Automatic font sizing";
        String campaignView = "Friend claim page";
        String newContentValue = "Disabled";

        CommonScenarios.navigateToAdminUrl();

        MceScenarios.openMultiCampaignEditor(campaignNameSA, TEST, campaignView, localizationName, localizationType);

        // Verify Campaign view field on MCE screen
        Assert.assertEquals(MceScenarios.getCampaignViewValue(localizationType), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(MceScenarios.getContentValue(localizationType), localizationName);

        //Verify campaigns count in Selected, Unselected and Ineligible sections
        MceScenarios.assertCampaignsCountInGrids(localizationType,
                "1",
                "3",
                "0");

        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNamePP);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(localizationType, "2", "2");

        MceScenarios.updateContentValue(localizationType, newContentValue);

        MceScenarios.returnToSimpleEditorAndAssertContentValue(localizationType, localizationName, newContentValue);
        MceScenarios.assertContentValueInSimpleEditor(campaignNamePP, TEST, campaignView, localizationType, localizationName, newContentValue);

    }

    /*
     *****  Verification of MCE Page for IMAGES localization type *******
     */
    @Test(groups = "updateContent", dependsOnMethods = "precondition")
    public void updateImages() {
        //test data:
//        EditorPage.LocalizationType localizationType = IMAGES;
        String localizationName = "Advocate signup page background";
        String campaignView = "Advocate signup page";
        String newContentValue = "tkbl_default_icon-link-color-2x.png";

        CommonScenarios.navigateToAdminUrl();

        MceScenarios.openMultiCampaignEditor(campaignNameSA, TEST, campaignView, localizationName, IMAGES);

        // Verify Campaign view field on MCE screen
        Assert.assertEquals(MceScenarios.getCampaignViewValue(IMAGES), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(MceScenarios.getContentValue(IMAGES), localizationName);

        //Verify campaigns count in Selected, Unselected and Ineligible sections
        MceScenarios.assertCampaignsCountInGrids(IMAGES,
                "1",
                "1",
                "2");

        MceScenarios.selectCampaignOnMceScreen(IMAGES, campaignNameFW_1);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(IMAGES, "2", "0");

        MceScenarios.updateContentValue(IMAGES, newContentValue);

        MceScenarios.returnToSimpleEditorAndAssertContentValue(IMAGES, localizationName, newContentValue);
        MceScenarios.assertContentValueInSimpleEditor(campaignNameFW_1, TEST, campaignView, IMAGES, localizationName, newContentValue);

    }


    /*
    Additional Scenarios for MCE: Filtering, Preview
    */
    @Test(groups = "additionalCases", dependsOnMethods = "precondition")//dependsOnGroups = "updateContent", alwaysRun = true)
    public void preconditionForAdditionalScenarios(){
        CommonScenarios.navigateToAdminUrl();
        CampaignDetailsPage detailsPage = CommonScenarios.createAndLaunchCampaign(AdvocateDashboard, Standalone);
        detailsPage.campaignNavigationMenu.deactivateCampaign();

        CommonScenarios.createAndLaunchCampaign(Invite, FloatingWidget);
        CommonScenarios.createAndLaunchCampaign(Invite, Standalone);
        CommonScenarios.createAndLaunchCampaign(Invite, PostPurchase);

        MceScenarios.openMultiCampaignEditorPage(campaignNamePP,
                TEST,
                "Advocate share page",
                "Advocate pages overlay opacity",
                COPY );
    }


    @Test(groups = "additionalCases", dependsOnMethods = "preconditionForAdditionalScenarios")
    public void filterByStatus(){
        MceScenarios.typeToSearchField("live");
        MceScenarios.assertCampaignsCountInGrids(COPY,
                "1",
                "1",
                "1");
    }

    @Test(groups = "additionalCases", dependsOnMethods = "preconditionForAdditionalScenarios")
    public void filterByCampaignName(){
        MceScenarios.typeToSearchField("invite");
        MceScenarios.assertCampaignsCountInGrids(COPY,
                "1",
                "3",
                "2");
    }

//    @Test(groups = "additionalCases", dependsOnMethods = {"preconditionForAdditionalScenarios", "precondition"})
//    public void filterByCampaignId() {
//        String campaignID = new PageMultiCampaignEditor(COPY)
//                .getSelectedCampaigns()
//                .getCampaignsList()
//                .get(0).getId().substring(0, 5);
//        System.out.println("DEBAG: Campaign ID: <" + campaignID + ">");
//        MceScenarios.typeToSearchField("invite");
//        MceScenarios.assertCampaignsCountInGrids(COPY,
//                "1",
//                "0",
//                "0");
//    }


    @Test(groups = "preview", dependsOnGroups = "additionalCases", alwaysRun = true)
    public void verifyPreview(){
        new PageMultiCampaignEditor(COPY).typeToSearch("");
        MceScenarios.typeToSearchField("invite");
        MceScenarios.selectCampaignOnMceScreen(COPY, "Invite Floating Widget");
        String contentValue = MceScenarios.getContentValue(COPY);

        MceScenarios.openPreviewPopupOnMCE(COPY);

        Assert.assertEquals(
                MceScenarios.getContentNameFromPreviewPopup(COPY),
                "Advocate share page",
                "FAILED: Incorrect Content Name on Preview Popup");
        Assert.assertEquals(MceScenarios.getContentValueFromPreviewPopup(COPY),
                contentValue,
                "FAILED: Incorrect Content Value");

        MceScenarios.closePreviewPopup(COPY);
        MceScenarios.assertCampaignsCountInGrids(COPY,
                "2",
                "2",
                "2");
    }


    @Test(groups = "deleteCampaigns",
            dependsOnGroups = {"updateContent", "additionalCases", "preview"},
            alwaysRun = true)
    public void deleteTestCampaignsAndLogout() {
        CommonScenarios.navigateToAdminUrl();
        CommonScenarios.deleteAllCampaignsWithStatus(TEST);
        CommonScenarios.deleteAllCampaignsWithStatus(LIVE);
        CommonScenarios.deleteAllCampaignsWithStatus(DISABLED);
        CommonScenarios.logout();
    }

}
