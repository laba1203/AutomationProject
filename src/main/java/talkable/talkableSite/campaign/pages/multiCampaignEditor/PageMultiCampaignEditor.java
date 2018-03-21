package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;

import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.COPY;

public class PageMultiCampaignEditor extends AbstractTalkableSitePage
{
    private EditorPage.LocalizationMode mode;
    private ElmntBackToEditor backToEditorButton = new ElmntBackToEditor();
    private ElmntCampaignViewField campaignViewField = new ElmntCampaignViewField();
    private ElmntContentField contentField = new ElmntContentField();
    private ElmntCampaignFilter campaignFilter = new ElmntCampaignFilter();

    private ContentValueRecord newContentValue;

    public PageMultiCampaignEditor(EditorPage.LocalizationMode mode){
        this.mode = mode;
        newContentValue = getContentValueRecord(mode);
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

    public PageMultiCampaignEditor updateContent(String newValue){
        newContentValue.update(newValue);
        return new PageMultiCampaignEditor(mode);
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
}
