package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.Element;
import org.openqa.selenium.By;
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

    private static final By backToEditorBtnLctr = By.xpath("//*[contains(text(), 'Back to Editor')]/..");
    private static final By campaignViewFieldLctr = By.xpath("//*[contains(text(), 'Campaign view')]/../p");
    private static final By contentFieldLctr = By.xpath("//*[contains(text(), 'Content')]/../p");
    private static final By previewBtnLctr = By.cssSelector(".edit-field-grid > .base-btn");
    private static final By campaignFilterInputLctr = By.xpath("//*[contains(@class, 'campaignfilter')]/input");
    private static final By saveContentBtnLctr = By.xpath("//*[contains(text(), 'Save Content')]");

    private Element backToEditorButton = new Element(backToEditorBtnLctr, "Back to Editor button");
    private Element campaignViewField = new Element(campaignViewFieldLctr, "Campaign View field");
    private Element contentField = new Element(contentFieldLctr, "Content field");
    private Element previewButton = new Element(previewBtnLctr, "Preview button");
    private Element campaignFilter = new Element(campaignFilterInputLctr, "Campaign Filter input field");
    private Element saveContentBtn = new Element(saveContentBtnLctr, "SaveContent");

    private SimpleEditorPage.LocalizationType mode;


    public PageMultiCampaignEditor(SimpleEditorPage.LocalizationType mode){
        this.mode = mode;
//        getContentValueRecord(mode);
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
        saveContentBtn.click();
        waitSaving();
        return new PageMultiCampaignEditor(mode);
    }

    public PageMultiCampaignEditor typeToSearch(String text){
        campaignFilter.clear();
        campaignFilter.sendKeys(text);
        return new PageMultiCampaignEditor(this.mode);
    }

    public PreviewPopup openPreviewPopup(){
        previewButton.click();
        return new PreviewPopup(mode);
    }
}
