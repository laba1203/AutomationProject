package common.cases.functionalities;

import org.testng.Assert;
import talkable.talkableSite.campaign.pages.ab.tests.newAbTest.AbTestPage;
import talkable.talkableSite.campaign.pages.ab.tests.newAbTest.AbTestReportPage;
import talkable.talkableSite.campaign.pages.ab.tests.newAbTest.campaign.menu.CampaignAbTestsPage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnLaunchCampaignPage;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import util.logging.Log;

public class AbTests extends EditorScenarios{

    public static CampaignAbTestsPage openCampaignAbTestsPage(){
        return new CampaignNavigationMenuOnLaunchCampaignPage().openAbTestsPage();
    }

    public static AbTestReportPage openFirstContentAbTestFromCampaignPage(){
        return new CampaignAbTestsPage()
                .openFirstContentRow()
                .viewReport();
    }

    public static AbTestPage openNewAbTestPage(String view, SimpleEditorPage.LocalizationType mode, String localeName){
        AbTestPage page = new SimpleEditorPage()
                .switchViewByNameOnSimpleEditor(view)
                .searchLocale(localeName, mode)
                .clickCreateABTest(localeName + "#");
        Log.logRecord("Create new AB Test page is opened for <"+mode+"> content.");
        return page;
    }

    public static AbTestPage editVariantB(String newValue){
        return new AbTestPage()
                .editVariantB(newValue);
    }

    public static SimpleEditorPage saveAbTest(){
        return new AbTestPage().save();
    }

    public static void assertAbTestInSimpleEditor(String view, SimpleEditorPage.LocalizationType mode, String localeName, String expectedStatus){
        String actualStatus = new SimpleEditorPage()
                .switchViewByNameOnSimpleEditor(view)
                .switchTo(mode)
                .searchLocale(localeName, mode)
                .getFirstLocaleWithAbTest()
                .getStatus();
        Assert.assertEquals(actualStatus, expectedStatus, "Incorrect A/B Test status in Simple Editor page for locale <" + localeName + "> ( View = " + view + ").");
    }

    public static String getAbTestName(){
        return new AbTestPage().getAbTestName();
    }

    public static void assertAbTestNameInReport(String expectedName){
        Assert.assertEquals(
                new AbTestReportPage().getAbTestName(),
                expectedName,
                "Incorrect A/B Test name on the A/B Test Report Page."
        );
    }

    public static void assertVariantValueInReport(String variant, String expectedValue){
        Assert.assertEquals(
                new AbTestReportPage().getVariantValue(variant),
                expectedValue,
                "Incorrect variant value for variant <" + variant + "> on the A/B Test Report page."
        );
    }

    public static void assertAbTestStatusInReport(String expectedStatus){
        Assert.assertEquals(
                new AbTestReportPage().getStatus(),
                expectedStatus,
                "Incorrect AB Test status on the A/B Test Report page."
        );
    }

    public static String getImpressionsFromAbTestReport(){
        return new AbTestReportPage().getImpressions();
    }


}
