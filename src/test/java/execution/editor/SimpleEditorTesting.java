package execution.editor;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.CampaignType;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.*;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1jjxHr_cLNaSq3HVBgU_y6k_llNDpNyTZUoWPAJ9Aw20/edit
 * */
public class SimpleEditorTesting extends BaseTest {

    private static final String siteName = PropertyLoader.loadProperty("sites.name.editorTesting");
    private EditorPage editorPage;

    @BeforeClass
    public void precondition(){
        //login to Talkable and select site
        CommonScenarios.login(EnvFactory.getUser(), EnvFactory.getPassword()).selectByVisibleText(siteName);
        //Create new Campaign
        CommonScenarios.initiateCampaignCreation(CampaignType.Invite, CampaignPlacement.Standalone);
    }

    //Navigate to Simple Editor
    @Test
    public void test1_navigateToSimpleEditor(){
        editorPage = new CampaignDetailsPage().campaignNavigationMenu.openEditorPage();
    }

    // Edit Copy content
    // Edit color content
    // Edit configuration content
    // Edit image content
    @Test(dataProvider = "editContentTestData")
    public void test2_editContent(EditorPage.LocalizationMode mode, String localizationName, String newValue)
    {
        editContentTest(mode, localizationName, newValue);
    }


    // Switch to Friend Share Email
    // Update attribute at Friend Share Email page
    @Test
    public void test3_updateFriendShareEmail(){
        String localizationName = "Friend share email copy#";
        String newValue = "Updated value for auto-test";

        editorPage = new EditorPage(COPY).switchViewByName("Friend share email");
        editContentTest(COPY, localizationName, newValue);

    }

    private void editContentTest(EditorPage.LocalizationMode mode, String localizationName, String newValue){
        editorPage = new EditorPage(mode);
//        System.out.println("DEBAG: **** Value: <" + editorPage.getLocalizationValue(mode, localizationName) + ">");
        editorPage = editorPage.updateLocalization(mode, localizationName, newValue);
        Assert.assertEquals(editorPage.getLocalizationValue(mode, localizationName), newValue);
    }

    @AfterClass
    public void deleteTestData(){
        CampaignDetailsPage detailsPage = editorPage.campaignNavigationMenu.openDetailsPage();
        detailsPage.delete();
    }




    @DataProvider
    public Object[][] editContentTestData() {
        return new Object[][]{
                {COPY, "Advocate signup page preheader#", "Updated value"},
                {COLOR, "Advocate signup page cta background#", "#0fef4e"},
                {CONFIGURATION, "Advocate signup button corners#", "Circle"},
                {IMAGES, "Advocate signup page background#", "tkbl_default_example.jpg"},
                //todo: For now IMAGE test is failed because of different values in expected and actual results.
                //To fix it method to compare partial text should be implemented
        };
    }

}