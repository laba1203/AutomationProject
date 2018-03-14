package execution.editor;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.CampaignType;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.campaignEditorPage.EditorPage;
import talkable.talkableSite.headerFrame.Header;
import util.EnvFactory;
import util.PropertyLoader;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1jjxHr_cLNaSq3HVBgU_y6k_llNDpNyTZUoWPAJ9Aw20/edit
 * */
public class SimpleEditorTesting extends BaseTest {

    private static final String siteName = PropertyLoader.loadProperty("sites.name.editorTesting");

    @BeforeClass
    public void precondition(){
        //login to Talkable and select site
        CommonScenarios.login(EnvFactory.getUser(), EnvFactory.getPassword()).selectByVisibleText(siteName);
        //Create new Campaign
        CommonScenarios.initiateCampaignCreation(CampaignType.Invite, CampaignPlacement.Standalone);
    }

    @Test
    public void test1_navigateToSimpleEditor(){
        new CampaignDetailsPage().campaignNavigationMenu.openEditorPage();
    }

    @Test
    public void test2_editCopyContent(){
        EditorPage editorPage = new EditorPage();
    }



}
