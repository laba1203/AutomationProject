package talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;

public class PreviewPopup extends AbstractElementsContainer{

    private EditorPage.LocalizationMode mode;

    private ElmntCampaignsDropDown campaigns = new ElmntCampaignsDropDown();
    private ElmntContentName contentName = new ElmntContentName();
    private ElmntContentValue contentValue = new ElmntContentValue();
    private ElmntCloseButton closeButton = new ElmntCloseButton();
    private ElmntViewPresetDropDown viewPreset;


    public PreviewPopup(EditorPage.LocalizationMode mode) {
        this.mode = mode;
    }

    public String getContentName(){
        return contentName.getText();
    }

    public String getContentValue(){
        return contentValue.getText();
    }

    public PageMultiCampaignEditor closePopup(){
        closeButton.click();
        return new PageMultiCampaignEditor(mode);
    }

    public String getViewPresetValue(){
        viewPreset = new ElmntViewPresetDropDown();
        return viewPreset.getSelectedItemText();
    }

}
