package execution.editor;

import common.cases.CommonScenarios;
import common.cases.functionalities.EditorScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.common.CampaignPlacement.Standalone;
import static talkable.common.CampaignType.Invite;


/** Scenario#1. Create new view (Advocate Offer Email).
     * 1. Login to Talkbale.
     * 2. Create new Campaign.
     * 3. Open Html Editor
     * 4. Create new View
     * 5. Verify new preset in the views list
     * */

	/*Link to test scenario: https://docs.google.com/spreadsheets/d/148sQEgtaeSuPrwor1s5HIFFqrgG4QEFR2_Gi2K3-r5k/edit#gid=0
     * */
public class HtmlEditorTesting extends BaseTest {

    private String user = PropertyLoader.loadProperty("talkable.user.editor");
    private String pswrd = EnvFactory.getPassword();


    @Test
    public void creteNewPreset(){
        String viewType = "Advocate Offer Email";
        String viewNameInHtmlEditor = "Advocate offer email";

        //login and clear test data:
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(user, pswrd);
        CommonScenarios.openCampaignsPage();
//        CommonScenarios.deleteAllCampaignsWithStatus(TEST);
        CommonScenarios.createNewCampaignFromCampaignsPage(Invite, Standalone);
        EditorScenarios.openHtmlEditor();
        EditorScenarios.createNewView(viewType);
        Assert.assertEquals(
                EditorScenarios.getSelectedView(),
                viewNameInHtmlEditor,
                "FAILED: Newly created view <" + viewNameInHtmlEditor + "> is not selected."
        );
    }

}
