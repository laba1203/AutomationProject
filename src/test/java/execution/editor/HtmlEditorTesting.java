package execution.editor;

import common.cases.CommonScenarios;
import common.cases.functionalities.EditorScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
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
        String view = "Advocate Offer Email";

        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(user, pswrd);
        CommonScenarios.openCampaignsPageAndCreateCampaign(Invite, Standalone);
        EditorScenarios.openHtmlEditor();
        EditorScenarios.createNewView(view);
        Assert.assertEquals(
                EditorScenarios.getSelectedView(),
                view,
                "FAILED: Newly created view <" + view + "> is not selected."
        );
    }

}
