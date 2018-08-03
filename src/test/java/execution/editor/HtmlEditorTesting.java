package execution.editor;

import common.cases.CommonScenarios;
import common.cases.functionalities.EditorScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.common.elements.alert.Alert;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.reports.staticAssets.StaticAssetsReportPage;
import util.EnvFactory;
import util.PropertyLoader;
import util.logging.Log;

import static talkable.common.CampaignPlacement.Standalone;
import static talkable.common.CampaignType.Invite;
import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.COPY;


/** Common Precondition:
 *      1. Accept Cookies Usage.
 *      2. Login
 *      3. Open Campaigns page.
 *      4. Deleted campaigns from the previous execution.
 *      5. Create New Campaign.
 *      6. Get link to campaign Details
 *
 * Scenario#1. Create new view (Advocate Offer Email).
     * 1. Open campaign details.
     * 3. Open Html Editor
     * 4. Create new View
     * 5. Verify new preset in the views list
     *
 *
 * Scenario#2. Delete view.
 *    1. Open campaign details.
 *    2. Open HTML Editor.
 *    3. Delete view.
 *    4. Verify that view is not present in the dropdown list
 *

   Scenario#3. Update email subject in Extra
        1. Open campaign details
        2.Open HTML Editor.
        3. Switch view to 'Friend share email'
        4. Open Extra popup.
        5. Update Email Subject
        6. Verify Email Subject in the preview frame.

 * Scenario#4. Edit HTML.
 *    1. Open campaign details.
 *    2. Open HTML Editor.
 *    3. Add copy locale, config locale and HTML to display those locales
 *    4. Verify copy locale in the preview frame.
 *    5. Verify config locale in the preview frame.
 *
 * Scenario#5. Edit CSS
 *    1. Open campaign details.
 *    2. Open HTML Editor.
 *    3. Add CSS row for some class
 *    4. Open any other Advocate view
 *    5. Verify that the row is preset.
 *    6. Open some Friend view.
 *    7. Verify that the row is not preset.
 *
 *    Scenario#6. Upload image
 *    1. Open campaign details.
 *    2. Open HTML Editor.
 *    3. Upload new image
 *    4. Verify image in the Files list.
 *
 *    Scenario#7. Upload font
 *    1. Open Static assets report
 *    2. Delete fonts.
 *    3. Open campaign details.
 *    4. Open HTML Editor.
 *    5. Upload font image
 *    6. Verify image in the Files list.
 *    */


	/*Link to test scenario: https://docs.google.com/spreadsheets/d/148sQEgtaeSuPrwor1s5HIFFqrgG4QEFR2_Gi2K3-r5k/edit#gid=0
     * */
	@Listeners(util.Listeners.class)
public class HtmlEditorTesting extends BaseTest {

    private String user = PropertyLoader.loadProperty("talkable.user.editor");
    private String pswrd = EnvFactory.getPassword();
    private String standaloneCampaignDetailsPageUrl;


    @BeforeClass
    public void loginAndCreateSite(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(user, pswrd);
    }

    @BeforeGroups("ui-actions")
    public void createStandaloneCampaign(){
        CommonScenarios.navigateToAdminUrl();
        CommonScenarios.openCampaignsPageAndCreateCampaign(Invite, Standalone);
        standaloneCampaignDetailsPageUrl = driver.getCurrentUrl();
    }

    @BeforeMethod
    public void openCampaignDetailsPage(){
        try {
            CommonScenarios.navigateToUrl(standaloneCampaignDetailsPageUrl);
            new CampaignDetailsPage();
        }catch (UnhandledAlertException e){
            new Alert().confirm();
            openCampaignDetailsPage();
        }
        Log.logRecord("Navigated to Campaign Details page URL: " + standaloneCampaignDetailsPageUrl);
    }


    /*Scenarios1*/
    @Test(groups = {"ui-actions"})
    public void creteNewView(){
        String viewType = "Advocate Offer Email";
        String viewNameInHtmlEditor = "Advocate offer email";

        EditorScenarios.openHtmlEditor();
        EditorScenarios.createNewView(viewType);
        Assert.assertEquals(
                EditorScenarios.getSelectedView(),
                viewNameInHtmlEditor,
                "FAILED: Newly created view <" + viewNameInHtmlEditor + "> is not selected."
        );
    }


    /*Scenarios2*/
    @Test(groups = {"ui-actions"}, dependsOnMethods = "editCSS", alwaysRun = true)
    public void deleteView(){
        String viewName = "Advocate share page";

        EditorScenarios.openHtmlEditor();
        EditorScenarios.deleteView(viewName);
        Assert.assertEquals(
                EditorScenarios.isViewPresent(viewName),
                false,
                "FAILED: View was not deleted."
        );
    }


    /*Scenario3*/
    @Test(groups = {"ui-actions"})
    public void updateEmailSubjectInExtraPopup(){
        String viewName = "Friend share email";
        String newSubject = "Automation test email subject";

        EditorScenarios.openHtmlEditor();
        EditorScenarios.switchViewByName(viewName);
        EditorScenarios.updateExtraEmailSubject(newSubject);
        Assert.assertEquals(
                EditorScenarios.getEmailSubjectFromPreview(),
                newSubject,
                "FAILED: Email subject is not updated in the HTML Editor Preview frame."
        );

    }


    @Test(groups = {"ui-actions"})
    public void editHTML(){
//        {% assign copy_localization1 = "copy_localization1" | localize: "Text"%}
//        {% assign copy_localization2 = "copy_localization2" | localize: "Text2", "Test3"%}
//        <h1 class = 'copy-field-autotest'>{{copy_localization1}}</h1>
//        <br>
//        <button class = 'copy-field-autotest-2'>
//            {{copy_localization2}}
//        </button>

        //test data:
        String copyVariable = "localization1";
        String copyValue = "TEXT";
        String copyVarClass = "copy-field-autotest";
        String configVariable = "config_localization1";
        String configValue1 = "TEXT1";
        String configValue2 = "TEXT2";
        String confVarClass = "conf-field-autotest";

        String liquidCode =
                "{% assign "+copyVariable+" = \""+copyVariable+"\" | localize: \""+copyValue+"\"%}\n" +
                "{% assign "+configVariable+" = \""+configVariable+"\" | localize: \""+configValue1+"\", \""+configValue2+"\"%}";

        String htmlCode =
                "        <h1 class = '"+copyVarClass+"'>{{"+copyVariable+"}}</h1>\n" +
                "        <br>\n" +
                "        <button class = '"+confVarClass+"'>\n" +
                "            {{"+configVariable+"}}\n" +
                "        </button>";
        String xpathOfCopyLocale = "//h1[@class='"+copyVarClass+"']";
        String xpathOfConfigLocale = "//button[@class='"+confVarClass+"']";

        EditorScenarios.openHtmlEditor();
        EditorScenarios.addNewHtmlCode(liquidCode + "\r\n" + htmlCode + "\r\n");

        Assert.assertEquals(
                EditorScenarios.getElementTextFromPreview(By.xpath(xpathOfCopyLocale)),
                copyValue,
                "Incorrect value in the Copy localization (xPath = '" + xpathOfCopyLocale + "')."
        );

        Assert.assertEquals(
                EditorScenarios.getElementTextFromPreview(By.xpath(xpathOfConfigLocale)),
                configValue1,
                "Incorrect value in the Config localization (xPath = '" + xpathOfConfigLocale + "')."
        );
        //search new localization in simple editor
        EditorScenarios.switchToSimpleEditor();
        EditorScenarios.searchLocalization(copyVariable, COPY);

    }


    @Test(groups = {"ui-actions"})
    public void editCSS() {
        //test data:
        String cssCode = ".new-autotest-class{ width: 200px; }";
        String advocateView = "Advocate share page";
        String friendView = "Friend claim page";

        EditorScenarios.openHtmlEditor();
        EditorScenarios.addNewCssCode(cssCode + "\n");
        EditorScenarios.assertFirstCssRow(cssCode);
        //verify css on another advocate view:
        EditorScenarios.switchViewByName(advocateView);
        EditorScenarios.assertFirstCssRow(cssCode);
        //verify css on another friend view:
        EditorScenarios.switchViewByName(friendView);
        Assert.assertNotEquals(
                EditorScenarios.getFirstCssRow(),
                cssCode,
                "CSS code was updated in the row#1 on the HTML Editor page for <" + friendView + "> view when it was added on Advocate View.");

    }

    /*Scenario#6*/
    @Test(groups = {"ui-actions"})
    public void uploadImage(){
        String imageName = "test.png";

        EditorScenarios.openHtmlEditor();
        EditorScenarios.uploadNewImage(imageName);

        Assert.assertEquals(
                EditorScenarios.getFirstImageNameFromFiles(),
                imageName,
                "Incorrect first image name in the Files popup when new image was uploaded."
        );
    }

    /*Scenario#7*/
    @Test(groups = {"ui-actions"})
    public void uploadFont(){
        //test data:
        String fontName = "AutomationFont";
        String woffFile = "KalishaScript.woff";
        String woff2File = "KalishaScript.woff2";

        ReportsScenarios.openStaticAssetsReport();
        ReportsScenarios.filterByNameInStaticAssets("KalishaScript");
        ReportsScenarios.deleteAllRowsFromStaticAssetsReport();
        CommonScenarios.navigateToUrl(standaloneCampaignDetailsPageUrl);

        EditorScenarios.openHtmlEditor();
        EditorScenarios.uploadFont(fontName, woffFile, woff2File);
        EditorScenarios.openCampaignDetailsFromEditor();
        ReportsScenarios.openStaticAssetsReport();
        ReportsScenarios.filterByNameInStaticAssets(woffFile);
        ReportsScenarios.assertRowsCountFromReport("2", new StaticAssetsReportPage());

    }



}
