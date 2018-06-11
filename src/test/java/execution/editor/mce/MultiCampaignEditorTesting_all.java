package execution.editor.mce;

import common.cases.CommonScenarios;
import common.cases.functionalities.MceScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.common.CampaignPlacement.*;
import static talkable.common.CampaignType.AdvocateDashboard;
import static talkable.common.CampaignType.Invite;
import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.*;
import static talkable.talkableSite.campaignsPage.Table.Status.*;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1NlY_NBmvKIRjmqb2d7oQPuDZEs6s7fToZkrSJPhwOaY
 * */
public class MultiCampaignEditorTesting_all extends BaseTest {

    private static final String siteName = PropertyLoader.loadProperty("sites.name.multiCampaignEditorTesting");

    //name of campaigns used in test:
    private String campaignNameFW_1 = "Invite Floating Widget";
    private String campaignNameSA = "Invite Standalone";
    private String campaignNameFW_2 = "Advocate Dashboard Floating Widget";
    private String campaignNamePP = "Invite Post Purchase";

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

        CommonScenarios.openCampaignsPage();

        //verify existing test data nad if
       if(!CommonScenarios.getCampaignsCountFromCampaignsPage(LIVE).equals("3")){
           CommonScenarios.deleteAllCampaignsWithStatus(LIVE);

           CommonScenarios.createAndLaunchCampaign(Invite, FloatingWidget);
           CommonScenarios.openCampaignsPage();
           CommonScenarios.createAndLaunchCampaign(Invite, Standalone);
           CommonScenarios.openCampaignsPage();
           CommonScenarios.createAndLaunchCampaign(Invite, PostPurchase);
       }

       CommonScenarios.openCampaignsPage();
       if(!CommonScenarios.getCampaignsCountFromCampaignsPage(TEST).equals("4")){
           CommonScenarios.deleteAllCampaignsWithStatus(TEST);

           campaignNameSA = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, Standalone);
           CommonScenarios.openCampaignsPage();
           campaignNameFW_1 = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, FloatingWidget);
           CommonScenarios.openCampaignsPage();
           campaignNameFW_2 = CommonScenarios.createNewCampaignFromCampaignsPage(AdvocateDashboard, FloatingWidget);
           CommonScenarios.openCampaignsPage();
           campaignNamePP = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, PostPurchase);
       }

       CommonScenarios.openCampaignsPage();
       if(!CommonScenarios.getCampaignsCountFromCampaignsPage(DISABLED).equals("1")){
           CommonScenarios.deleteAllCampaignsWithStatus(DISABLED);

           CampaignDetailsPage detailsPage = CommonScenarios.createAndLaunchCampaign(AdvocateDashboard, Standalone);
           detailsPage.campaignNavigationMenu.deactivateCampaign();
       }
    }


    @Test (groups = "updateContent", dependsOnMethods = "precondition")
    public void modifyCOPY() {
        //test data:
        SimpleEditorPage.LocalizationType mode = COPY;
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
                "2",
                "5");

        //select second campaign:
        MceScenarios.selectCampaignOnMceScreen(mode, campaignNameFW_2);

        //verify Selected and Unselected campaigns count
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(mode, "2", "1");

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
        SimpleEditorPage.LocalizationType localizationType = COLOR;
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
                "7",
                "0");

        MceScenarios.typeToSearchField("test", COLOR);
        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNameFW_1);
        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNameSA);
        MceScenarios.typeToSearchField(" ", COLOR);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(localizationType, "3", "5");

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
        SimpleEditorPage.LocalizationType localizationType = CONFIGURATION;
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
                "7",
                "0");

        MceScenarios.typeToSearchField("test", CONFIGURATION);
        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNamePP);
        MceScenarios.typeToSearchField(" ", CONFIGURATION);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(localizationType, "2", "6");

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
//        SimpleEditorPage.LocalizationType localizationType = IMAGES;
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
                "3",
                "4");

        MceScenarios.typeToSearchField("test", IMAGES);
        MceScenarios.selectCampaignOnMceScreen(IMAGES, campaignNameFW_1);
        MceScenarios.typeToSearchField(" ", IMAGES);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(IMAGES, "2", "2");

        MceScenarios.updateContentValue(IMAGES, newContentValue);

        MceScenarios.returnToSimpleEditorAndAssertContentValue(IMAGES, localizationName, newContentValue);
        MceScenarios.assertContentValueInSimpleEditor(campaignNameFW_1, TEST, campaignView, IMAGES, localizationName, newContentValue);

    }


    /*
    Additional Scenarios for MCE: Filtering, Preview
    */


    @Test(groups = "additionalCases", dependsOnMethods = "precondition")
    public void filterByStatus(){
        CommonScenarios.navigateToAdminUrl();
        MceScenarios.openMultiCampaignEditorPage(campaignNamePP,
                TEST,
                "Advocate share page",
                "Advocate pages overlay opacity",
                COPY );
        MceScenarios.typeToSearchField("live", COPY);
        MceScenarios.assertCampaignsCountInGrids(COPY,
                "0",
                "2",
                "1");
    }

    @Test(groups = "additionalCases", dependsOnMethods = "precondition")
    public void filterByCampaignName(){
        CommonScenarios.navigateToAdminUrl();
        MceScenarios.openMultiCampaignEditorPage(campaignNamePP,
                TEST,
                "Advocate share page",
                "Advocate pages overlay opacity",
                COPY );
        MceScenarios.typeToSearchField("invite", COPY);
        MceScenarios.assertCampaignsCountInGrids(COPY,
                "1",
                "3",
                "2");
    }

    @Test(groups = "preview", dependsOnGroups = "additionalCases", alwaysRun = true)
    public void verifyPreview(){
        CommonScenarios.navigateToAdminUrl();
        MceScenarios.openMultiCampaignEditorPage(campaignNamePP,
                TEST,
                "Advocate share page",
                "Advocate pages overlay opacity",
                COPY );
//        new PageMultiCampaignEditor(COPY).typeToSearch("");
        MceScenarios.typeToSearchField("invite", COPY);
        MceScenarios.selectCampaignOnMceScreen(COPY, "Invite Floating Widget");
        String contentValue = MceScenarios.getNewContentValue(COPY);

        MceScenarios.openPreviewPopupOnMCE(COPY);

        Assert.assertEquals(
                MceScenarios.getContentNameFromPreviewPopup(COPY),
//                "Advocate share page",
                "Advocate pages overlay opacity",
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


//    @Test(groups = "deleteCampaigns",
//            dependsOnGroups = {"updateContent", "additionalCases", "preview"},
//            alwaysRun = true)
    public void deleteTestCampaignsAndLogout() {
        CommonScenarios.navigateToAdminUrl();
        CommonScenarios.deleteAllCampaignsWithStatus(TEST);
        CommonScenarios.deleteAllCampaignsWithStatus(LIVE);
        CommonScenarios.deleteAllCampaignsWithStatus(DISABLED);
        CommonScenarios.logout();
    }

}
