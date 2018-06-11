package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.common.elements.alert.Alert;
import talkable.talkableSite.AbstractTkblSitePageWithoutHeader;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;
import util.logging.Log;

public class AbstractEditorPage extends AbstractTkblSitePageWithoutHeader
{
    private static final By presetDropDownBtnLctr = By.xpath("//*[@data-editor-toggle = 'presets']");
    private static final By presetWasRemovedMsg = By.xpath("//*[contains(text(), 'Preset was removed')]");
    public CampaignNavigationMenuOnEditor campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
    private ElmntSelectedViewField elmntSelectedViewField = new ElmntSelectedViewField();

    void switchViewByName(String name){
        if(isViewSelected(name)) {
            System.out.println("DEBAG: View <" + name + "> is already selected");
        }else{
            elmntSelectedViewField.click();
            new ContainerViewRecords().selectViewByText(name);
            System.out.println("DEBAG: View changed to : " + name);
        }
    }

    //    public SimpleEditorPage switchViewByIndex(int index){
//        elmntSelectedViewField.click();
//        new ContainerViewRecords().selectByIndex(index);
//        return new SimpleEditorPage();
//    }

    private boolean isViewSelected(String toBeSelected){
        return elmntSelectedViewField.getText().equals(toBeSelected);
    }

    public ViewPresetFrame openPresetDropDown(){
        clickToPresetDropDown();
        return new ViewPresetFrame();
    }

    public void clickToPresetDropDown(){
        new Element(presetDropDownBtnLctr, "Preset Dropdown").click();
    }



    public String getSelectedPresetName(){
        return new Element(presetDropDownBtnLctr, "Preset Dropdown").getText();
    }

    public SimpleEditorPage deletePresetOnSimpleEditor(String presetName) {
        openPresetDropDown()
                .findPresetByName(presetName)
                .deletePreset();

        new Element(presetWasRemovedMsg);
        Log.logRecord("View Preset with name <" + presetName + "> is deleted");
        return new SimpleEditorPage();
    }

    public HtmlEditorPage deletePresetOnHtmlEditor(String presetName) {
        openPresetDropDown()
                .findPresetByName(presetName)
                .deletePreset();
        new Alert().confirm();
//        new Element(presetWasRemovedMsg);
        Log.logRecord("View Preset with name <" + presetName + "> is deleted");
        return new HtmlEditorPage();
    }

    public HtmlEditorPage createNewPreset(String presetName, String jsonVariables){
        return openPresetDropDown()
                .clickCreateNewPreset()
                .closeParentWindowAndCreateNewPreview(presetName, jsonVariables);
    }

    public boolean isPresetPreset(String presetName){
        ViewPresetFrame frame = openPresetDropDown();
        try {
            frame.findPresetByName(presetName);
            Log.logRecord("View Preset with name <" + presetName + "> is present in the preset drop down in Editor");
            return true;
        }catch (AssertionError e){
            Log.logRecord("View Preset with name <" + presetName + "> is not available in the preset drop down in Editor");
            return false;
        }
    }
}
