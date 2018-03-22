package execution.editor.mce;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.CampaignType;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.headerFrame.Header;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.COPY;
import static talkable.talkableSite.campaignsPage.Table.Status.TEST;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1NlY_NBmvKIRjmqb2d7oQPuDZEs6s7fToZkrSJPhwOaY
 * */
public class MultiCampaignEditorTesting extends BaseTest{

    private static final String siteName = PropertyLoader.loadProperty("sites.name.multiCampaignEditorTesting");
    private static final String newCopyValue = "New Copy Value";
    private String campaignNameFW_1;;
    private String campaignNameSA;
    private String campaignNameFW_2;
    private String campaignNamePP;

    private String content = "Advocate trigger cta";

    private PageMultiCampaignEditor mcePage;

    @BeforeClass
    public void precondition(){
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

    // Open FW campaign.
    // Navigate to Simple Editor --> Copy (AD Trigger Widget view)
    // For attribute "Advocate trigger cta" click 'Copy to other campaign' button
    @Test
    public void test1_openMultiCampaignEditor(){
        String campaignView = "Advocate trigger widget";

        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(campaignNameFW_1, TEST);
        EditorPage editor = detailsPage.campaignNavigationMenu.openEditorPage();
        mcePage = editor.clickCopyToOtherCampaigns(COPY, content + "#");

        // Verify Campaign view field on MCE screen
        Assert.assertEquals(mcePage.getCampaignViewValue(), campaignView);
        // Verify Content field on MCE screen
        Assert.assertEquals(mcePage.getContentValue(), content);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "1");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "1");
        // Verify ineligible campaign list
        Assert.assertEquals(mcePage.getIneligibleCampaigns().getCampaignCount(), "2");
    }

    //Select second FW campaign
    @Test
    public void test2_selectSecondFWCampaign(){
        mcePage.selectCampaign(campaignNameFW_2);
        // Verify selected campaign list
        Assert.assertEquals(mcePage.getSelectedCampaigns().getCampaignCount(), "2");
        // Verify unselected campaigns list
        Assert.assertEquals(mcePage.getUnselectedCampaigns().getCampaignCount(), "0");
    }

    //Enter new value and click Save content button
    @Test
    public void test3_updateValue(){
        mcePage = mcePage.updateContent(newCopyValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newCopyValue);
    }

    @Test
    public void test4_checkValueInFirstCampaign(){
        EditorPage editor = mcePage.backToEditor();
        String value = editor.getLocalizationValue(COPY, content + "#");
        Assert.assertEquals(value, newCopyValue);
    }

    @Test
    public void test5_checkValueInSecondCampaign(){
        //open campaignDetailsPage and open second campaign:
        PageCampaigns campaignsPage = new EditorPage().campaignNavigationMenu
                .openDetailsPage().header.openCampaignsPage();
        EditorPage editor = campaignsPage.openCampaignByName(campaignNameFW_2, TEST).campaignNavigationMenu.openEditorPage();
        // Verify value in Editor:
        String value = editor.getLocalizationValue(COPY, content + "#");
        Assert.assertEquals(value, newCopyValue);
    }

    @AfterClass
    public void deleteTestCampaigns(){
        CampaignDetailsPage detailsPage = new EditorPage().campaignNavigationMenu.openDetailsPage();
        detailsPage.header.openCampaignsPage().deleteAllTestCampaigns();
    }
}
