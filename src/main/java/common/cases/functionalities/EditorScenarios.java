package common.cases.functionalities;

import common.cases.CommonScenarios;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaignsPage.Table;
import util.logging.Log;

public class EditorScenarios extends CommonScenarios{

    public static void openSimpleEditorForCampaign(String campaignName, Table.Status campaignStatus){
        openCampaignDetailsPageFor(campaignName, campaignStatus);
        openSimpleEditor();
    }

    public static void openSimpleEditor(){
        new CampaignNavigationMenu().openEditorPage();
        Log.logRecord("Simple Editor is opened");
    }

    public static void updateLocalization(EditorPage.LocalizationType type, String view, String localizationName, String newValue){
        EditorPage editorPage = new EditorPage(type);
        editorPage = editorPage.switchViewByNameOnSimpleEditor(view);
        editorPage.updateLocalization(type, localizationName, newValue);
        Log.logRecord("Localization <" + type + "." + localizationName + "> changed to <" + newValue + "> on view <" + view + ">");
    }

    public static String getLocalizationValue(EditorPage.LocalizationType type, String localizationName){
        return new EditorPage(type).getLocalizationValue(type, localizationName);
    }



}
