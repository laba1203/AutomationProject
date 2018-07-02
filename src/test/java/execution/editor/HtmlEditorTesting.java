package execution.editor;

import common.cases.CommonScenarios;
import common.cases.functionalities.EditorScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.common.CampaignPlacement.Standalone;
import static talkable.common.CampaignType.Invite;
import static talkable.talkableSite.campaignsPage.Table.Status.TEST;


/** Scenario#1. Create new view (Advocate Offer Email).
     * 1. Login to Talkbale.
     * 2. Create new Campaign.
     * 3. Open Html Editor
     * 4. Create new View
     * 5. Verify new preset in the views list
     *
 *
 *    Scenario2. Delete view.
 *    1. */


	/*Link to test scenario: https://docs.google.com/spreadsheets/d/148sQEgtaeSuPrwor1s5HIFFqrgG4QEFR2_Gi2K3-r5k/edit#gid=0
     * */
public class HtmlEditorTesting extends BaseTest {

    private String user = PropertyLoader.loadProperty("talkable.user.editor");
    private String pswrd = EnvFactory.getPassword();
    private String campaignDetailsPageUrl;


    @BeforeGroups("ui-actions")
    public void precondition(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(user, pswrd);
        CommonScenarios.openCampaignsPage();
        CommonScenarios.deleteAllCampaignsWithStatus(TEST);
        CommonScenarios.createNewCampaignFromCampaignsPage(Invite, Standalone);
        campaignDetailsPageUrl = driver.getCurrentUrl();
    }



    @Test(groups = {"ui-actions"})
    public void creteNewView(){
        String viewType = "Advocate Offer Email";
        String viewNameInHtmlEditor = "Advocate offer email";

        driver.navigate().to(campaignDetailsPageUrl);
        EditorScenarios.openHtmlEditor();
        EditorScenarios.createNewView(viewType);
        Assert.assertEquals(
                EditorScenarios.getSelectedView(),
                viewNameInHtmlEditor,
                "FAILED: Newly created view <" + viewNameInHtmlEditor + "> is not selected."
        );
    }

    //Blocked by the Defect: https://talkable.atlassian.net/browse/PR-9486
//    @Test(groups = {"ui-actions"})
    public void deleteView(){
        String viewName = "Advocate share page";

        driver.navigate().to(campaignDetailsPageUrl);
        EditorScenarios.openHtmlEditor();
        EditorScenarios.deleteView(viewName);

        Assert.assertEquals(
                EditorScenarios.isViewPresent(viewName),
                false,
                "FAILED: View was not deleted."
        );
    }


}
