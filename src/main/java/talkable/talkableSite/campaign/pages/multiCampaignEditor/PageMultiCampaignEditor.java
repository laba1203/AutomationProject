package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen.PreviewPopup;

import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.COPY;
import static talkable.talkableSite.campaign.pages.multiCampaignEditor.CampaignsList.State.INELIGIBLE;
import static talkable.talkableSite.campaign.pages.multiCampaignEditor.CampaignsList.State.SELECTED;
import static talkable.talkableSite.campaign.pages.multiCampaignEditor.CampaignsList.State.UNSELECTED;

public class PageMultiCampaignEditor extends AbstractTalkableSitePage
{
    private SimpleEditorPage.LocalizationType mode;
    private ElmntBackToEditor backToEditorButton = new ElmntBackToEditor();
    private ElmntCampaignViewField campaignViewField = new ElmntCampaignViewField();
    private ElmntContentField contentField = new ElmntContentField();
    private ElmntCampaignFilter campaignFilter = new ElmntCampaignFilter();
    private ElmntPreviewButton previewButton = new ElmntPreviewButton();

    public PageMultiCampaignEditor(SimpleEditorPage.LocalizationType mode){
        this.mode = mode;
        getContentValueRecord(mode);
        new ElmntSaveContentButton();
    }

    private ContentValueRecord getContentValueRecord(SimpleEditorPage.LocalizationType mode){
        switch (mode){
            case COPY:
                return new ContentCopyInput();
            case CONFIGURATION:
                return new ContentConfigInput();
            case COLOR:
                return new ContentColorInput();
            case IMAGES:
                return new ContentImageInput();
            default:
                Assert.fail("FAILED: Unknown localization mode");
                return null;
        }
    }

    public String getNewContentValue(){
        return getContentValueRecord(mode).getText();
    }

    public PageMultiCampaignEditor updateContent(String newValue){
        getContentValueRecord(mode).update(newValue);
        return saveChanges();
    }

    public SimpleEditorPage backToEditor(){
        backToEditorButton.click();
        return new SimpleEditorPage(COPY);
    }

    public String getCampaignViewValue() {
        return campaignViewField.getText();
    }

    public String getContentValueName() {
        return contentField.getText();
    }

    public CampaignsList getIneligibleCampaigns() {
        return new CampaignsList(INELIGIBLE);
    }

    public CampaignsList getSelectedCampaigns() {
        return new CampaignsList(SELECTED);
    }

    public CampaignsList getUnselectedCampaigns() {
        return new CampaignsList(UNSELECTED);
    }

    public PageMultiCampaignEditor selectCampaign(String campaignName){
        getUnselectedCampaigns().findCampaign(campaignName).select();
        return new PageMultiCampaignEditor(this.mode);
    }

    public PageMultiCampaignEditor unselectCampaign(String campaignName){
        getSelectedCampaigns().findCampaign(campaignName).select();
        return new PageMultiCampaignEditor(this.mode);
    }

    private PageMultiCampaignEditor saveChanges(){
        new ElmntSaveContentButton().click();
        waitSaving();
        return new PageMultiCampaignEditor(mode);
    }

    public PageMultiCampaignEditor typeToSearch(String text){
        new ElmntCampaignFilter().clear();
        new ElmntCampaignFilter().sendKeys(text);
        return new PageMultiCampaignEditor(this.mode);
    }

    public PreviewPopup openPreviewPopup(){
        previewButton.click();
        return new PreviewPopup(mode);
    }
}
