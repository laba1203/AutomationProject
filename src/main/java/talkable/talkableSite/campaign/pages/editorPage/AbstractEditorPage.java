package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTkblSitePageWithoutHeader;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;
import util.logging.Log;

public abstract class AbstractEditorPage extends AbstractTkblSitePageWithoutHeader
{
    private static final By presetDropDownBtnLctr = By.xpath("//*[@data-editor-toggle = 'presets']");
    private static final By presetWasRemovedMsg = By.xpath("//*[contains(text(), 'Preset was removed')]");
    public CampaignNavigationMenuOnEditor campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
    private ElmntSelectedViewField elmntSelectedViewField = new ElmntSelectedViewField();

    public void switchViewByName(String name){
        if(isViewSelected(name)) {
            System.out.println("DEBAG: View <" + name + "> is already selected");
        }else{
            elmntSelectedViewField.click();
            new ContainerViewRecords().selectViewByText(name);
            System.out.println("DEBAG: View changed to : " + name);
        }

    }

    //    public EditorPage switchViewByIndex(int index){
//        elmntSelectedViewField.click();
//        new ContainerViewRecords().selectByIndex(index);
//        return new EditorPage();
//    }

    private boolean isViewSelected(String toBeSelected){
        return elmntSelectedViewField.getText().equals(toBeSelected);
    }

    private ViewPresetFrame openPresetDropDown(){
        new Element(presetDropDownBtnLctr, "Preset Dropdown").click();
        return new ViewPresetFrame();
    }

    public String getSelectedPresetName(){
        return new Element(presetDropDownBtnLctr, "Preset Dropdown").getText();
    }

    public EditorPage deletePreset(String presetName) {
        openPresetDropDown()
                .findPresetByName(presetName)
                .deletePreset();
        new Element(presetWasRemovedMsg);
        Log.logRecord("View Preset with name <" + presetName + "> is deleted");
        return new EditorPage();
    }

    public EditorPage createNewPreset(String presetName){
        openPresetDropDown()
                .findPresetByName(presetName);
        //todo: methos is not completed!!!!
        return null;
    }
}
