package execution.ab.tests;

import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import common.cases.ExternalScenarios;
import common.cases.functionalities.AbTests;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import util.EnvFactory;
import util.PropertyLoader;
import util.Util;

import static talkable.common.CampaignPlacement.Standalone;
import static talkable.common.CampaignType.Invite;
import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.COPY;

@Listeners(util.Listeners.class)
public class LocalesAbTests extends BaseTest{
    private String tkblUser = PropertyLoader.loadProperty("talkable.user.ab.tests");
    private String tkblPswrd = EnvFactory.getPassword();
    private String gitHubUserName = PropertyLoader.loadProperty("github.user");
    private String gitHubPswrd = PropertyLoader.loadProperty("github.password");
    private String siteLocation = "ab-tests/";
    private String sitePage = "index.html";
    private String gitHubEditorPage = EnvFactory.getGitHubEditLink() + siteLocation + sitePage;
    private String customerSiteUrl = EnvFactory.getGitHubSiteLink() + siteLocation;
    //test

    private String tkblSiteUrl;
    private String campaignUrl;

    @BeforeClass
    public void precondition(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(tkblUser, tkblPswrd, customerSiteUrl);
        tkblSiteUrl = CommonScenarios.getCurrentUrl();
    }

    @BeforeGroups("test-with-integrated-site")
    public void createIntegratedSiteWithActiveCampaign(){
//        CommonScenarios.navigateToUrl(tkblSiteUrl);
//        String tkblIntegrationScript = CommonScenarios
//                .getSiteIntegrationValues()
//                .getTalkableIntegrationScript();
//        ExternalScenarios.editGiHubFile(
//                gitHubEditorPage,
//                gitHubUserName,
//                gitHubPswrd,
//                Util.generateItegratedPageCode(tkblIntegrationScript));
        CommonScenarios.navigateToUrl(tkblSiteUrl);
        CommonScenarios.openCampaignsPage();
        CommonScenarios.createAndLaunchCampaign(Invite, Standalone);
        campaignUrl = CommonScenarios.getCurrentUrl();
    }


    @Test(groups = "test-with-integrated-site")
    public void createCopyAbTest(){
        //test data:
        SimpleEditorPage.LocalizationType mode = COPY;
        String view = "Advocate signup page";
        String localeName = "Advocate signup page description";
        String variant = "B";
        String valueB = "Automation test variant";
        String expectedStatus = "Inactive";
        String abTestName;

        AbTests.navigateToUrl(campaignUrl);
        AbTests.openSimpleEditor();
        AbTests.openNewAbTestPage(view, mode, localeName);
        abTestName = AbTests.getAbTestName();
        AbTests.editVariantB(valueB);
        AbTests.saveAbTest();
        AbTests.assertAbTestInSimpleEditor(view, mode, localeName, expectedStatus);
        AbTests.openCampaignAbTestsPage();
        AbTests.openFirstContentAbTestFromCampaignPage();
        AbTests.assertAbTestNameInReport(abTestName);
        AbTests.assertVariantValueInReport(variant, valueB);
        AbTests.assertAbTestStatusInReport(expectedStatus);
        String abTestReportUrl = AbTests.getCurrentUrl();

        //todo: below part is not yet completed and doesn't work properly.

//        //make impression:
//        ClientSiteScenarios.assertCampaignOnCustomerSite(Invite, Standalone, customerSiteUrl);
//        AbTests.navigateToUrl(abTestReportUrl);
//        //verify A/B Test status
//        expectedStatus = "Active";
//        AbTests.assertAbTestStatusInReport(expectedStatus);
//        Assert.assertEquals(
//                AbTests.getImpressionsFromAbTestReport(),
//                "1",
//                "Incorrect impressions count in the A/B Test report."
//        );
    }


}
