package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.common.elements.alert.Alert;
import talkable.talkableSite.AbstractTkblSitePageWithoutHeader;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;
import util.WaitFactory;
import util.logging.Log;

public class AbstractEditorPage extends AbstractTkblSitePageWithoutHeader
{
    private static final By presetDropDownBtnLctr = By.xpath("//*[@data-editor-toggle = 'presets']");
    private static final By presetWasRemovedMsg = By.xpath("//*[contains(text(), 'Preset was removed')]");
    private static final By htmlEditorBtnLctr = By.xpath("//li[@class='subnav-actions-toggler']//a[contains(text(), 'HTML')]");
    private static final By simpleEditorBtnLctr = By.xpath("//li[@class='subnav-actions-toggler']//a[text() = 'Editor']");
//    private static final By previewFrameLctr = By.xpath("//iframe[contains(@class, 'cover-iframe')]");
    private static final By selectedViewFieldLctr = By.cssSelector(".editor-view-setup-switcher span");
    private static final By emailSubjectInPreview = By.xpath("//*[@class ='editor-preview-data-bottom-part']//*[contains(text(), 'Subject')]/following::span[contains(@class, 'preview-data-text')]");
    private static final By saveBtnLctr = By.xpath("//div[contains(text(), 'save changes')]");


    public CampaignNavigationMenuOnEditor campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
    Element elmntSelectedViewField = new Element(selectedViewFieldLctr, "Selected View field");
    private PreviewFrame previewFrame = new PreviewFrame();
//    private Element previewIFrame = new Element(previewFrameLctr);
    private Element saveButton = new Element(saveBtnLctr, "Save Changes button");

    void switchViewByName(String name){
        if(isViewSelected(name)) {
            Log.logRecord("View <" + name + "> is already selected");
        }else{
            openViewList().selectViewByText(name);
            Log.logRecord("View changed to : " + name);
        }
    }

    public AbstractEditorPage switchView(String viewName){
        switchViewByName(viewName);
        waitFactory().waitUntilTextToBePresentInElement(selectedViewFieldLctr, viewName, 4);
        Log.logRecord("View switched to <" + viewName + ">.");
        return new AbstractEditorPage();
    }

    ContainerViewRecords openViewList(){
        elmntSelectedViewField.click();
        return new ContainerViewRecords();
    }



    public HtmlEditorPage openHtmlEditor(){
        new Element(htmlEditorBtnLctr, "HTML & CSS tab").click();
        return new HtmlEditorPage();
    }

    public SimpleEditorPage openSimpleEditor(){
        new Element(simpleEditorBtnLctr, "Simple Editor tab").click();
        SimpleEditorPage page = new SimpleEditorPage(SimpleEditorPage.LocalizationType.COPY);
        Log.logRecord("Switched to Simple Editor tab.");
        return page;
    }

    private boolean isViewSelected(String toBeSelected){
        return getSelectedViewName().equals(toBeSelected);
    }

    public String getSelectedViewName(){
        return elmntSelectedViewField.getText();
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

    public boolean isViewPresent(String viewName){
       return openViewList()
               .isViewPresent(viewName);
    }

    public String getElementTextFromPreviewFrame(By locator){
        return previewFrame.getElementText(locator);
    }

    public String getEmailSubjectFromPreview(){
        return new Element(emailSubjectInPreview).getText();
    }

    protected void saveChanges(){
        saveButton.click();
        waitSaving();
    }
}
