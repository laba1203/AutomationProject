package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen.PreviewPopup;

import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.COPY;
import static talkable.talkableSite.campaign.pages.multiCampaignEditor.CampaignsList.State.INELIGIBLE;
import static talkable.talkableSite.campaign.pages.multiCampaignEditor.CampaignsList.State.SELECTED;
import static talkable.talkableSite.campaign.pages.multiCampaignEditor.CampaignsList.State.UNSELECTED;

public class PageMultiCampaignEditor extends AbstractTalkableSitePage
{
    private EditorPage.LocalizationMode mode;
    private ElmntBackToEditor backToEditorButton = new ElmntBackToEditor();
    private ElmntCampaignViewField campaignViewField = new ElmntCampaignViewField();
    private ElmntContentField contentField = new ElmntContentField();
    private ElmntCampaignFilter campaignFilter = new ElmntCampaignFilter();
    private ElmntSaveContentButton saveContentButton = new ElmntSaveContentButton();
    private CampaignsList selectedCampaigns;
    private CampaignsList unselectedCampaigns;
    private CampaignsList ineligibleCampaigns;

    private ElmntPreviewButton previewButton = new ElmntPreviewButton();

    private ContentValueRecord contentRecord;

    public PageMultiCampaignEditor(EditorPage.LocalizationMode mode){
        this.mode = mode;
        contentRecord = getContentValueRecord(mode);
        setCampaignsLists();

        System.out.println("DEBAG: MCE page is opened");
    }

    private ContentValueRecord getContentValueRecord(EditorPage.LocalizationMode mode){
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
        return contentRecord.getText();
    }

    public PageMultiCampaignEditor updateContent(String newValue){
        contentRecord.update(newValue);
        return saveChanges();
    }

    public EditorPage backToEditor(){
        backToEditorButton.click();
        return new EditorPage(COPY);
    }

    public String getCampaignViewValue() {
        return campaignViewField.getText();
    }

    public String getContentValue() {
        return contentField.getText();
    }

    public CampaignsList getIneligibleCampaigns() {
        return ineligibleCampaigns;
    }

    public CampaignsList getSelectedCampaigns() {
        return selectedCampaigns;
    }

    public CampaignsList getUnselectedCampaigns() {
        return unselectedCampaigns;
    }

    private void setCampaignsLists(){
        selectedCampaigns = new CampaignsList(SELECTED);
        unselectedCampaigns = new CampaignsList(UNSELECTED);
        ineligibleCampaigns = new CampaignsList(INELIGIBLE);
    }

    public void selectCampaign(String campaignName){
        unselectedCampaigns.findCampaign(campaignName).select();
        setCampaignsLists();
    }

    public void unselectCampaign(String campaignName){
        selectedCampaigns.findCampaign(campaignName).select();
        setCampaignsLists();
    }

    private PageMultiCampaignEditor saveChanges(){
        new ElmntSaveContentButton().click();
        waitSaving();
        return new PageMultiCampaignEditor(mode);
    }

    public void typeToSearch(String text){
        campaignFilter.clear();
        campaignFilter.sendKeys(text);
        setCampaignsLists();
    }

    public PreviewPopup openPreviewPopup(){
        previewButton.click();
        return new PreviewPopup(mode);
    }
}
