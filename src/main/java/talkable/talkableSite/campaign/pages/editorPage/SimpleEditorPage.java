package talkable.talkableSite.campaign.pages.editorPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.editorPage.localizationSidebar.LocalizationSidebar;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;

import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.*;

public class SimpleEditorPage extends AbstractEditorPage{//AbstractTkblSitePageWithoutHeader{

    private static final By presetDropDownBtnLctr = By.xpath("//*[@data-editor-toggle = 'presets']");
    private static final By presetWasRemovedMsg = By.xpath("//*[contains(text(), 'Preset was removed')]");

    public CampaignNavigationMenuOnEditor campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
    private LocalizationSidebar localizationSidebar;
    private ElmntSaveButton saveButton = new ElmntSaveButton();
    private ElmntSelectedViewField elmntSelectedViewField = new ElmntSelectedViewField();
    private ElmntCopyButton copyButton = new ElmntCopyButton();
    private ElmntImagesButton imagesButton = new ElmntImagesButton();
    private ElmntColorButton colorButton = new ElmntColorButton();
    private ElmntConfigurationButton configurationButton = new ElmntConfigurationButton();
    private PreviewFrame previewFrame = new PreviewFrame();

    public enum LocalizationType {COPY, IMAGES, COLOR, CONFIGURATION}
    private LocalizationType mode;

    public SimpleEditorPage(){
        switchTo(COPY);
        setLocalizationSidebar(COPY);
    }

    private void setLocalizationSidebar(LocalizationType mode){
        localizationSidebar = new LocalizationSidebar(mode);
        this.mode = mode;
    }

    public SimpleEditorPage(LocalizationType mode){
        switchTo(mode);
        localizationSidebar = new LocalizationSidebar(mode);
        this.mode = mode;
    }

    public String getElementTextFromPreviewFrame(By locator){
        return previewFrame.getElementText(locator);
    }

//    public SimpleEditorPage switchViewByIndex(int index){
//        elmntSelectedViewField.click();
//        new ContainerViewRecords().selectByIndex(index);
//        return new SimpleEditorPage();
//    }

    /*commented due to new parent class*/

//    public SimpleEditorPage switchViewByName(String name){
//        if(isViewSelected(name)) {
//            System.out.println("DEBAG: View <" + name + "> is already selected");
//        }else{
//            elmntSelectedViewField.click();
//            new ContainerViewRecords().selectViewByText(name);
//            System.out.println("DEBAG: View changed to : " + name);
//        }
//
//        return new SimpleEditorPage(this.mode);
//    }

    public SimpleEditorPage switchViewByNameOnSimpleEditor(String name){
        super.switchViewByName(name);
        return new SimpleEditorPage(this.mode);
    }

    private boolean isViewSelected(String toBeSelected){
        return elmntSelectedViewField.getText().equals(toBeSelected);
    }

    public SimpleEditorPage updateLocalization(LocalizationType type, String localizationName, String newValue){
        verifyLocalizationMode(type);
        localizationSidebar.updateRecord(type, localizationName, newValue);
        saveChanges();
        return new SimpleEditorPage(type);
    }


    public String getLocalizationValue(LocalizationType type, String localizationName){
        return localizationSidebar.getRecord(type, localizationName).getValueText();
    }

    private void verifyLocalizationMode(LocalizationType mode){
        Assert.assertEquals(mode, this.mode, "FAILED: Incorrect Localization type used in the method");
    }

    private SimpleEditorPage saveChanges(){
        saveButton.click();
        waitSaving();
        return new SimpleEditorPage(this.mode);
    }

    /*commented due to new parent class*/
//    private ViewPresetFrame openPresetDropDown(){
//        new Element(presetDropDownBtnLctr, "Preset Dropdown").click();
//        return new ViewPresetFrame();
//    }

    /*commented due to new parent class*/
//    public String getSelectedPresetName(){
//        return new Element(presetDropDownBtnLctr, "Preset Dropdown").getText();
//    }

    /*commented due to new parent class*/
//    public SimpleEditorPage deletePreset(String presetName) {
//        openPresetDropDown()
//                .findPresetByName(presetName)
//                .deletePreset();
//        new Element(presetWasRemovedMsg);
//        Log.logRecord("View Preset with name <" + presetName + "> is deleted");
//        return new SimpleEditorPage();
//    }

    /*commented due to new parent class*/
//    public SimpleEditorPage createNewPreset(String presetName){
//        openPresetDropDown()
//                .findPresetByName(presetName)
//                .
//    }


    public void switchTo(LocalizationType mode){
        switch (mode){
            case COPY:
                wait.until(ExpectedConditions.elementToBeClickable(copyButton.getWebElement()));
                copyButton.moveToElementAndClick();
                setLocalizationSidebar(COPY);
                break;
            case COLOR:
                wait.until(ExpectedConditions.elementToBeClickable(colorButton.getWebElement()));
                colorButton.moveToElementAndClick();
                setLocalizationSidebar(COLOR);
                break;
            case IMAGES:
                wait.until(ExpectedConditions.elementToBeClickable(imagesButton.getWebElement()));
                imagesButton.moveToElementAndClick();
                setLocalizationSidebar(IMAGES);
                break;
            case CONFIGURATION:
                wait.until(ExpectedConditions.elementToBeClickable(configurationButton.getWebElement()));
                configurationButton.moveToElementAndClick();
                setLocalizationSidebar(CONFIGURATION);
                break;
            default:
                Assert.fail("FAILED: Unknown localization type: <" + mode + ">");
                break;
        }
    }

    public PageMultiCampaignEditor clickCopyToOtherCampaigns(LocalizationType type, String localizationName){
        localizationSidebar.getRecord(type, localizationName).copyToOtherCampaigns();
        return new PageMultiCampaignEditor(mode);
    }

    public void clickCreateABTest(LocalizationType type, String localizationName){
        localizationSidebar.getRecord(type, localizationName).createABTest();
        //TODO: Return action to be added after implementation of AB Test editor page
    }
}
