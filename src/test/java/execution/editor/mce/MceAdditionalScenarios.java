package execution.editor.mce;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen.PreviewPopup;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.campaignsPage.Table;
import util.PropertyLoader;

import static talkable.talkableSite.campaign.pages.CampaignPlacement.FloatingWidget;
import static talkable.talkableSite.campaign.pages.CampaignPlacement.PostPurchase;
import static talkable.talkableSite.campaign.pages.CampaignPlacement.Standalone;
import static talkable.talkableSite.campaign.pages.CampaignType.AdvocateDashboard;
import static talkable.talkableSite.campaign.pages.CampaignType.Invite;
import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.COPY;
import static talkable.talkableSite.campaignsPage.Table.Status.DISABLED;
import static talkable.talkableSite.campaignsPage.Table.Status.LIVE;
import static talkable.talkableSite.campaignsPage.Table.Status.TEST;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1P-VCSCq04_lqZQXhbhEm09duImidJBJGbIaOnmIlLDg
 * */
public class MceAdditionalScenarios extends BaseTest{

    private static final String siteName = PropertyLoader.loadProperty("sites.name.multiCampaignEditorTesting");
    private static final String localizationName = "Advocate pages overlay opacity";

    private static final String incorrectSelectedCampaignsMsg = "FAILED: Incorrect Selected campaigns count";
    private static final String incorrectUnselectedCampaignsMsg = "FAILED: Incorrect Unselected campaigns count";
    private static final String incorrectIneligibleCampaignsMsg = "FAILED: Incorrect Ineligible campaigns count";

    private PageMultiCampaignEditor mcePage;
    private PreviewPopup previewPopup;


    @BeforeClass
    public void testDataSetup(){
        // create test data
        CampaignDetailsPage detailsPage = CommonScenarios.createAndLaunchCampaign(AdvocateDashboard, Standalone);
        detailsPage.campaignNavigationMenu.deactivateCampaign();

        CommonScenarios.createAndLaunchCampaign(Invite, FloatingWidget);
        CommonScenarios.createAndLaunchCampaign(Invite, Standalone);
        detailsPage = CommonScenarios.createAndLaunchCampaign(Invite, PostPurchase);

        // open MCE for PP campaign:
        EditorPage editor = detailsPage.campaignNavigationMenu.openEditorPage();
        mcePage = editor.clickCopyToOtherCampaigns(COPY, localizationName + "#");
    }


    @Test(groups = "filtering")
    public void test11_filterByStatus(){
        mcePage = mcePage.typeToSearch("live");
        assertCampaignsCount(mcePage, "1", "1", "1");
    }

    @Test(groups = "filtering")
    public void test12_filterByCampaignName(){
        mcePage = mcePage.typeToSearch("invite");
        assertCampaignsCount(mcePage, "1", "3", "2");
    }

    @Test(groups = "filtering")
    public void test13_filterByCampaignId(){
        String campaignID = mcePage.getSelectedCampaigns().getCampaignsList().get(0).getId().substring(0, 5);
        System.out.println("DEBAG: Campaign ID: <" + campaignID + ">");
        mcePage = mcePage.typeToSearch(campaignID);
        assertCampaignsCount(mcePage, "1", "0", "0");
    }

    @AfterGroups("filtering")
    public void clearFilter(){
        mcePage = mcePage.typeToSearch("");
    }


    @BeforeGroups("preview")
    public void selectMultipleCampaigns(){
        mcePage = mcePage.typeToSearch("invite");
        mcePage = mcePage.selectCampaign("Invite Floating Widget");
    }

    @Test(groups = "preview", dependsOnGroups = "filtering", alwaysRun = true)
    public void test21_verifyPreviewPopup(){
        String contentValue = mcePage.getNewContentValue();
        previewPopup = mcePage.openPreviewPopup();

        Assert.assertEquals(previewPopup.getContentName(), localizationName, "FAILED: Incorrect Content Name");
        Assert.assertEquals(previewPopup.getContentValue(), contentValue, "FAILED: Incorrect Content Value");
    }

    @Test(groups = "preview", dependsOnMethods = "test21_verifyPreviewPopup", alwaysRun = true)
    public void test22_closePreviewPopup(){
        if(previewPopup == null){
            Assert.fail("FAILED: Preview popup is not opened.");
        }
        mcePage = previewPopup.closePopup();
        assertCampaignsCount(mcePage, "2", "3", "3");
    }

    @AfterClass
    public void deactivateAndDelete(){
        PageCampaigns campaignsPage = mcePage.header.openCampaignsPage().deleteAllCampaignsWithStatus(TEST);
        campaignsPage = campaignsPage.deleteAllCampaignsWithStatus(LIVE);
        campaignsPage.deleteAllCampaignsWithStatus(DISABLED);
    }

    private void assertCampaignsCount(PageMultiCampaignEditor mcePage,
                                      String expectedSelectedCount,
                                      String expectedUnselectedCount,
                                      String expectedIneligibleCount){
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), expectedSelectedCount, incorrectSelectedCampaignsMsg);
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), expectedUnselectedCount, incorrectUnselectedCampaignsMsg);
        Assert.assertEquals(mcePage.getIneligibleCampaigns().getCampaignCount(), expectedIneligibleCount, incorrectIneligibleCampaignsMsg);
    }

}
