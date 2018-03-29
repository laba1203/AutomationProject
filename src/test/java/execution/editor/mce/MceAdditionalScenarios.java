package execution.editor.mce;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import talkable.talkableSite.campaignsPage.Table;
import talkable.talkableSite.headerFrame.Header;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.talkableSite.campaign.pages.CampaignPlacement.FloatingWidget;
import static talkable.talkableSite.campaign.pages.CampaignPlacement.PostPurchase;
import static talkable.talkableSite.campaign.pages.CampaignPlacement.Standalone;
import static talkable.talkableSite.campaign.pages.CampaignType.AdvocateDashboard;
import static talkable.talkableSite.campaign.pages.CampaignType.Invite;
import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.COPY;
import static talkable.talkableSite.campaignsPage.Table.Status.LIVE;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1P-VCSCq04_lqZQXhbhEm09duImidJBJGbIaOnmIlLDg
 * */
public class MceAdditionalScenarios extends BaseTest{

    private static final String siteName = PropertyLoader.loadProperty("sites.name.multiCampaignEditorTesting");
    //ToDo: test cases for filtering and preview verification

    private PageMultiCampaignEditor mcePage;

    private static final String incorrectSelectedCampaignsMsg = "FAILED: Incorrect Selected campaigns count";
    private static final String incorrectUnselectedCampaignsMsg = "FAILED: Incorrect Unselected campaigns count";
    private static final String incorrectIneligibleCampaignsMsg = "FAILED: Incorrect Ineligible campaigns count";

    @BeforeClass
    public void testDataSetup(){
        //Temp:
        CommonScenarios.login(EnvFactory.getUser(), EnvFactory.getPassword()).selectByVisibleText(siteName);
        // create test data
//        CommonScenarios.initiateCampaignCreation(AdvocateDashboard, Standalone);
//        CampaignDetailsPage detailsPage = CommonScenarios.launchCampaign();
//        detailsPage.campaignNavigationMenu.deactivateCampaign();
//
//        CommonScenarios.initiateCampaignCreation(Invite, FloatingWidget);
//        CommonScenarios.launchCampaign();
//        CommonScenarios.initiateCampaignCreation(Invite, Standalone);
//        CommonScenarios.launchCampaign();
//
//        CommonScenarios.initiateCampaignCreation(Invite, PostPurchase);
//        detailsPage = CommonScenarios.launchCampaign();
        // open MCE for PP campaign:
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CampaignDetailsPage detailsPage = new Header()
                .openCampaignsPage()
                .openCampaignByName("Invite Post Purchase", LIVE);
        EditorPage editor = detailsPage.campaignNavigationMenu.openEditorPage();
        mcePage = editor.clickCopyToOtherCampaigns(COPY, "Advocate pages overlay opacity#");

    }


    @Test(groups = "filtering")
    public void test11_filterByStatus(){
        mcePage.typeToSearch("live");
        assertCampaignsCount(mcePage, "1", "1", "1");
    }

    @Test(groups = "filtering")
    public void test12_filterByCampaignName(){
        mcePage.typeToSearch("invite");
        assertCampaignsCount(mcePage, "1", "3", "2");
    }

    @Test(groups = "filtering")
    public void test13_filterByCampaignId(){
        String campaignID = mcePage.getSelectedCampaigns().getCampaignsList().get(0).getId().substring(0, 5);
        System.out.println("DEBAG: Campaign ID: <" + campaignID + ">");
        mcePage.typeToSearch(campaignID);
        assertCampaignsCount(mcePage, "1", "0", "0");
    }

    @AfterGroups("filtering")
    public void clearFilter(){
        mcePage.typeToSearch("");
    }


    @BeforeGroups("preview")
    public void test21_selectMultipleCampaigns(){
        mcePage.selectCampaign("Invite Floating Widget");
        mcePage.selectCampaign("Invite Post Purchase");
    }

    @Test(groups = "preview")
    public void test22_preview(){
//        mcePage.
    }

    @AfterClass
    public void deactivateAndDelete(){

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
