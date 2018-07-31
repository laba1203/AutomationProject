package common.cases.functionalities;

import common.cases.CommonScenarios;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen.PreviewPopup;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.campaignsPage.Table;
import talkable.talkableSite.headerFrame.Header;
import util.logging.Log;

import static common.cases.functionalities.MceScenarios.State.INELIGIBLE;
import static common.cases.functionalities.MceScenarios.State.SELECTED;
import static common.cases.functionalities.MceScenarios.State.UNSELECTED;


public class MceScenarios extends CommonScenarios{

    public enum State{ SELECTED, UNSELECTED, INELIGIBLE }


    /*Scenarios to open Multi-Campaign Editor page for some campaign.
     * Precondition: Page with header should opened. Campaign with @campaignName should exist with defined @status.
     * 1. Navigate to Campaigns page
     * 2. Search campaign by @campaignName and @status
     * 3. Select campaign
     * 4. Navigate to Editor page.
     * 5. Select view by @pageViewName
     * 6. Select localization type by @contentType (COPY, IMAGE, CONFIGURATION or COLOR)
     * 7. Find localization by @localizationName and click 'Copy to Other Campaigns' button
     * @Returns: Multi-Campaign Editor page for mentioned parameters.
     * */
    public static PageMultiCampaignEditor openMultiCampaignEditor(String campaignName,
                                                                      Table.Status status,
                                                                      String pageViewName,
                                                                      String localizationName,
                                                                      SimpleEditorPage.LocalizationType contentType) {
        PageMultiCampaignEditor page = new Header()
                .openCampaignsPage()
                .openCampaignByName(campaignName, status)
                .campaignNavigationMenu
                .openEditorPage()
                .switchViewByNameOnSimpleEditor(pageViewName)
                .switchTo(contentType)
                .clickCopyToOtherCampaigns(contentType, localizationName + "#")
                ;
        Log.logRecord("Multi-Campaign Editor page is opened for campaign = <" + campaignName + ">, localization = <" + contentType + " --> " + localizationName + ">.");
        return page;
    }

    public static void selectCampaignOnMceScreen(SimpleEditorPage.LocalizationType localizationType, String campaignName){
        new PageMultiCampaignEditor(localizationType).selectCampaign(campaignName);
    }

    public static String getCampaignsCountFromMce(State campaignState, SimpleEditorPage.LocalizationType localizationMode){
        switch (campaignState){
            case SELECTED:
                return new PageMultiCampaignEditor(localizationMode).getSelectedCampaigns().getCampaignCount();
            case UNSELECTED:
                return new PageMultiCampaignEditor(localizationMode).getUnselectedCampaigns().getCampaignCount();
            case INELIGIBLE:
                return new PageMultiCampaignEditor(localizationMode).getIneligibleCampaigns().getCampaignCount();
            default:
                Assert.fail("FAILED: Unknown campaign state <" + campaignState.toString() + ">");
                return "";
        }
    }

    public static String getContentValue(SimpleEditorPage.LocalizationType mode){
        return new PageMultiCampaignEditor(mode).getContentValueName();
    }

    public static String getNewContentValue(SimpleEditorPage.LocalizationType mode){
        return new PageMultiCampaignEditor(mode).getNewContentValue();
    }


    public static String getCampaignViewValue(SimpleEditorPage.LocalizationType localizationType){
        return new PageMultiCampaignEditor(localizationType).getCampaignViewValue();
    }

    public static void updateContentValue(SimpleEditorPage.LocalizationType localizationType, String newContentValue){
        PageMultiCampaignEditor mcePage = new PageMultiCampaignEditor(localizationType)
                .updateContent(newContentValue);
        Assert.assertEquals(mcePage.getNewContentValue(), newContentValue, "FAILED: Incorrect new content value on MCE page when the value was updated");
    }

    public static void returnToSimpleEditorAndAssertContentValue(SimpleEditorPage.LocalizationType localizationType, String localizationName, String expectedContentValue){
        SimpleEditorPage editor = new PageMultiCampaignEditor(localizationType).backToEditor();
        editor.switchTo(localizationType);
        String value = editor.getLocalizationValue(localizationType, localizationName + "#");
        Assert.assertEquals(
                value,
                expectedContentValue,
                "FAILED: Incorrect new content value in Simple Editor when it was updated in MCE for localization type = " + localizationType.toString()
        );

    }

    public static void assertContentValueInSimpleEditor(String campaignName,
                                                        Table.Status campaignStatus,
                                                        String pageViewName,
                                                        SimpleEditorPage.LocalizationType localizationType,
                                                        String localizationName,
                                                        String expectedContentValue){
        PageCampaigns campaignsPage = new SimpleEditorPage()
                .campaignNavigationMenu
                .openDetailsPage()
                .header
                .openCampaignsPage();

        SimpleEditorPage editor = campaignsPage
                .openCampaignByName(campaignName, campaignStatus)
                .campaignNavigationMenu
                .openEditorPage();

        editor = editor.switchViewByNameOnSimpleEditor(pageViewName);
        editor.switchTo(localizationType);
        // Verify value in Editor:
        String value = editor.getLocalizationValue(localizationType, localizationName + "#");
        Assert.assertEquals(value, expectedContentValue,
                "FAILED: Incorrect new content value in Simple Editor when it was updated in MCE for campaign: <" + campaignName + ">");
    }

    public static void assertCampaignsCountInGrids(SimpleEditorPage.LocalizationType localizationType, String expectedSelectedCount, String expectedUnselectedCount, String expectedIneligibleCount){
        assertSelectedAndUnselectedCampaignsCount(localizationType, expectedSelectedCount, expectedUnselectedCount);
         // Verify ineligible campaign list
        Assert.assertEquals(getCampaignsCountFromMce(INELIGIBLE, localizationType), expectedIneligibleCount, "FAILED: Incorrect count of Ineligible campaigns on MCE page. Mode: " + localizationType + ".");
    }

    public static void assertSelectedAndUnselectedCampaignsCount(SimpleEditorPage.LocalizationType localizationType, String expectedSelectedCount, String expectedUnselectedCount){
        // Verify selected campaign list
        Assert.assertEquals(getCampaignsCountFromMce(SELECTED, localizationType), expectedSelectedCount, "FAILED: Incorrect count of Selected campaigns on MCE page. Mode: " + localizationType + ".");
        // Verify unselected campaigns list
        Assert.assertEquals(getCampaignsCountFromMce(UNSELECTED, localizationType), expectedUnselectedCount, "FAILED: Incorrect count of Unselected campaigns on MCE page. Mode: " + localizationType + ".");
    }


    public static void typeToSearchField(String searchInput, SimpleEditorPage.LocalizationType contentMode){
        new PageMultiCampaignEditor(contentMode).typeToSearch(searchInput);
    }

    public static void openPreviewPopupOnMCE(SimpleEditorPage.LocalizationType localizationType){
        new PageMultiCampaignEditor(localizationType).openPreviewPopup();
    }

    public static String getContentNameFromPreviewPopup(SimpleEditorPage.LocalizationType localizationType){
        return new PreviewPopup(localizationType).getContentName();
    }

    public static String getContentValueFromPreviewPopup(SimpleEditorPage.LocalizationType localizationType){
        return new PreviewPopup(localizationType).getContentValue();
    }

    public static void closePreviewPopup(SimpleEditorPage.LocalizationType localizationType){
        new PreviewPopup(localizationType).closePopup();
    }
}
