package execution.editor.mce;

import common.cases.CommonScenarios;
import common.cases.functionalities.MceScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.common.CampaignPlacement.*;
import static talkable.common.CampaignType.Invite;
import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.*;
import static talkable.talkableSite.campaignsPage.Table.Status.DISABLED;
import static talkable.talkableSite.campaignsPage.Table.Status.LIVE;
import static talkable.talkableSite.campaignsPage.Table.Status.TEST;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1NlY_NBmvKIRjmqb2d7oQPuDZEs6s7fToZkrSJPhwOaY
 * */
public class MceTests_ShortVersion extends BaseTest {

    private static final String user = PropertyLoader.loadProperty("talkable.user.mce");
    private static final String pswrd = EnvFactory.getPassword();

    //name of campaigns used in test:
    private String campaignNameFW_1 = "Invite Floating Widget";
    private String campaignNameSA = "Invite Standalone";
    private String campaignNamePP = "Invite Post Purchase";

    @Test(groups = "updateContent")
    public void precondition() {
        //login to Talkable and select site
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(user, pswrd);

        //create campaigns:
        CommonScenarios.openCampaignsPage();
        campaignNameFW_1 = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, FloatingWidget);
        CommonScenarios.launchCampaign();
        CommonScenarios.openCampaignsPage();
        campaignNameSA = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, Standalone);
        CommonScenarios.openCampaignsPage();
        campaignNamePP = CommonScenarios.createNewCampaignFromCampaignsPage(Invite, PostPurchase);
        CommonScenarios.launchCampaign();
        CommonScenarios.deactivateCampaignFromCampaignNavigationMenu();

    }


    @Test (dependsOnMethods = "precondition")
    public void modifyCOPY() {
        //test data:
        SimpleEditorPage.LocalizationType mode = COPY;
        String newContentValue = "New Copy Value";
        String campaignView = "Advocate signup page";
        String localizationName = "Advocate signup page cta";

        CommonScenarios.navigateToAdminUrl();
        MceScenarios.openMultiCampaignEditor(campaignNameSA, TEST, campaignView, localizationName, mode);
        // Verify Campaign view field on MCE screen
        Assert.assertEquals(MceScenarios.getCampaignViewValue(mode), campaignView, "Incorrect Campaign view value on the MCE page.");
        // Verify Content field on MCE screen
        Assert.assertEquals(MceScenarios.getContentValue(mode), localizationName, "Incorrect campaign Content value on the MCE page");
        //Verify campaigns count in Selected, Unselected and Ineligible sections
        MceScenarios.assertCampaignsCountInGrids(mode,
                "1",
                "1",
                "1");

        //select second campaign:
        MceScenarios.selectCampaignOnMceScreen(mode, campaignNameFW_1);
        //verify Selected and Unselected campaigns count
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(mode, "2", "0");
        //update content
        MceScenarios.updateContentValue(mode, newContentValue);
        //check new content value in the First Campaign
        MceScenarios.returnToSimpleEditorAndAssertContentValue(mode, localizationName, newContentValue);
        //checkValueInSecondCampaignCOPY
        MceScenarios.assertContentValueInSimpleEditor(campaignNameFW_1, LIVE, campaignView, mode, localizationName, newContentValue);
    }


/******  Verification of MCE Page for COLOR localization type ********/

    @Test(dependsOnMethods = "precondition")
    public void updateCOLOR() {
        //test data:
        SimpleEditorPage.LocalizationType localizationType = COLOR;
        String newContentValue = "#23f908";
        String localizationName = "Advocate share page email button background";
        String campaignView = "Advocate share page";

        CommonScenarios.navigateToAdminUrl();
        MceScenarios.openMultiCampaignEditor(campaignNamePP, DISABLED, campaignView, localizationName, localizationType);
        // Verify Campaign view field on MCE screen
        Assert.assertEquals(MceScenarios.getCampaignViewValue(localizationType), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(MceScenarios.getContentValue(localizationType), localizationName);
        //Verify campaigns count in Selected, Unselected and Ineligible sections
        MceScenarios.assertCampaignsCountInGrids(localizationType,
                "1",
                "2",
                "0");
        MceScenarios.typeToSearchField("test", COLOR);
        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNameSA);
        MceScenarios.typeToSearchField(" ", COLOR);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(localizationType, "2", "1");
        MceScenarios.updateContentValue(localizationType, newContentValue);
        MceScenarios.returnToSimpleEditorAndAssertContentValue(localizationType, localizationName, newContentValue);
        MceScenarios.assertContentValueInSimpleEditor(campaignNameSA, TEST, campaignView, localizationType, localizationName, newContentValue);

    }


     /******  Verification of MCE Page for CONFIGURATION localization type ********/

    @Test(dependsOnMethods = "precondition")
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
                "2",
                "0");
        MceScenarios.typeToSearchField("disabled", CONFIGURATION);
        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNamePP);
        MceScenarios.typeToSearchField(" ", CONFIGURATION);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(localizationType, "2", "1");
        MceScenarios.updateContentValue(localizationType, newContentValue);
        MceScenarios.returnToSimpleEditorAndAssertContentValue(localizationType, localizationName, newContentValue);
        MceScenarios.assertContentValueInSimpleEditor(campaignNamePP, DISABLED, campaignView, localizationType, localizationName, newContentValue);

    }


     /******  Verification of MCE Page for IMAGES localization type ********/
    @Test(dependsOnMethods = "precondition")
    public void updateImages() {
        //test data:
        SimpleEditorPage.LocalizationType localizationType = IMAGES;
        String localizationName = "Advocate signup page background";
        String campaignView = "Advocate signup page";
        String newContentValue = "tkbl_default_icon-link-color-2x.png";

        CommonScenarios.navigateToAdminUrl();
        MceScenarios.openMultiCampaignEditor(campaignNameSA, TEST, campaignView, localizationName, localizationType);
        // Verify Campaign view field on MCE screen
        Assert.assertEquals(MceScenarios.getCampaignViewValue(localizationType), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(MceScenarios.getContentValue(localizationType), localizationName);
        //Verify campaigns count in Selected, Unselected and Ineligible sections
        MceScenarios.assertCampaignsCountInGrids(localizationType,
                "1",
                "1",
                "1");
        MceScenarios.typeToSearchField("live", localizationType);
        MceScenarios.selectCampaignOnMceScreen(localizationType, campaignNameFW_1);
        MceScenarios.typeToSearchField(" ", localizationType);
        MceScenarios.assertSelectedAndUnselectedCampaignsCount(localizationType, "2", "0");
        MceScenarios.updateContentValue(localizationType, newContentValue);
        MceScenarios.returnToSimpleEditorAndAssertContentValue(localizationType, localizationName, newContentValue);
        MceScenarios.assertContentValueInSimpleEditor(campaignNameFW_1, LIVE, campaignView, localizationType, localizationName, newContentValue);

    }


    @Test(dependsOnMethods = "precondition")
    public void filterByCampaignName(){
        CommonScenarios.navigateToAdminUrl();
        MceScenarios.openMultiCampaignEditor(campaignNamePP,
                DISABLED,
                "Advocate share page",
                "Advocate pages overlay opacity",
                COPY );
        MceScenarios.typeToSearchField("invite post", COPY);
        MceScenarios.assertCampaignsCountInGrids(COPY,
                "1",
                "0",
                "0");
    }

}
