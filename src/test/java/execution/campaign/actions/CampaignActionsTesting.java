package execution.campaign.actions;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;

import static talkable.common.CampaignPlacement.FloatingWidget;
import static talkable.common.CampaignType.AdvocateDashboard;

public class CampaignActionsTesting extends BaseTest{

    @Test
    public void login(){
        CommonScenarios.login(
                PropertyLoader.loadProperty("talkable.user.campaignActionsTest"),
                EnvFactory.getPassword()
        );
    }

    @Test(dependsOnMethods = "login")
    public void copyCampaign(){
        String campaignName = "TestCampaign" + TestDataGenerator.getRandomId();
        String campaignDesc = "Some campaign description";

        CommonScenarios.openCampaignsPage();
        CommonScenarios.createNewCampaignFromCampaignsPage(AdvocateDashboard, FloatingWidget);
        CommonScenarios.openCampaignRulesPage();
        CommonScenarios.setCampaignNameOnRulesPage(campaignName);
        CommonScenarios.setCampaignDescriptionOnRulesPage(campaignDesc);

        CommonScenarios.switchRedirectOnExpiredClaimCheckboxOnRulesPage();
        CommonScenarios.switchPlainTextVersionCheckboxOnRulesPage();
        CommonScenarios.switchUseFacebookAppIdCheckboxOnRulesPage();
        CommonScenarios.launchCampaign();

        CommonScenarios.copyCampaignFromDetailsPage();
        Assert.assertEquals(
                CommonScenarios.getCampaignNameFromNavigationMenu(),
                "Copy of " + campaignName,
                "FAILED: Incorrect campaign name of the copied campaign.");
        Assert.assertEquals(
                CommonScenarios.getCampaignPlacementFromNavigationMenu(),
                FloatingWidget,
                "FAILED: Incorrect placement of the copied campaign.");
        CommonScenarios.openCampaignRulesPage();
        Assert.assertEquals(
                CommonScenarios.getCampaignDescriptionOnRulesPage(),
                campaignDesc,
                "FAILED: Incorrect description of the copied campaign."
        );
        Assert.assertEquals(
                CommonScenarios.getPlainTextVersionCheckboxOnRulesPage(),
                "on",
                "FAILED: Incorrect 'Enable Plain Text version for all email views' value in copied campaign.");
        Assert.assertEquals(
                CommonScenarios.getRedirectOnExpiredClaimCheckbox(),
                "on",
                "FAILED: Incorrect 'Redirect a Friend to a Site URL when his offer is expired' value in copied campaign.");
        Assert.assertEquals(
                CommonScenarios.getUseFacebookAppIdCheckboxValueFromRulesPage(),
                "on",
                "FAILED: Incorrect 'Use Facebook App Share instead of sharer.php' value in copied campaign."
        );
        Log.testPassed("Campaign successfully copied.");
    }


}
