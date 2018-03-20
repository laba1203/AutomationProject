package talkable.talkableSite.campaign.pages.editorPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.talkableSite.AbstractTkblSitePageWithoutHeader;
import talkable.talkableSite.campaign.pages.editorPage.localizationSidebar.LocalizationSidebar;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;

import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationMode.*;

public class EditorPage extends AbstractTkblSitePageWithoutHeader{

    public CampaignNavigationMenuOnEditor campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
    public LocalizationSidebar localizationSidebar;
    private ElmntSaveButton saveButton = new ElmntSaveButton();
    private ElmntSelectedViewField elmntSelectedViewField = new ElmntSelectedViewField();
    private ElmntCopyButton copyButton = new ElmntCopyButton();
    private ElmntImagesButton imagesButton = new ElmntImagesButton();
    private ElmntColorButton colorButton = new ElmntColorButton();
    private ElmntConfigurationButton configurationButton = new ElmntConfigurationButton();
    private PreviewFrame previewFrame = new PreviewFrame();

    public enum LocalizationMode{COPY, IMAGES, COLOR, CONFIGURATION}
    private LocalizationMode mode;

    public EditorPage(){
        switchTo(COPY);
        setLocalizationSidebar(COPY);
    }

    private void setLocalizationSidebar(LocalizationMode mode){
        localizationSidebar = new LocalizationSidebar(mode);
        this.mode = mode;
    }

    public EditorPage(LocalizationMode mode){
        switchTo(mode);
        localizationSidebar = new LocalizationSidebar(mode);
        this.mode = mode;
    }

    public String getElementTextFromPreviewFrame(By locator){
        return previewFrame.getElementText(locator);
    }

    public EditorPage switchViewByIndex(int index){
        elmntSelectedViewField.click();
        new ContainerViewRecords().selectByIndex(index);
        return new EditorPage();
    }

    public EditorPage switchViewByName(String name){
        elmntSelectedViewField.click();
        new ContainerViewRecords().selectViewByText(name);
        return new EditorPage(this.mode);
    }

    public EditorPage updateLocalization(LocalizationMode type, String localizationName, String newValue){
        verifyLocalizationMode(type);
        localizationSidebar.updateRecord(type, localizationName, newValue);
        saveChanges();
        return new EditorPage(type);
    }

    public EditorPage uploadAndSelectNewImage(String localizationName, String fileName){
        verifyLocalizationMode(IMAGES);
        //todo: add steps to upload new image
        Assert.fail("FAILED: Method is not yet implemented");
        return null;
    }

    public String getLocalizationValue(LocalizationMode type, String localizationName){
        return localizationSidebar.getRecord(type, localizationName).getValueText();
    }

    private void verifyLocalizationMode(LocalizationMode mode){
        Assert.assertEquals(mode, this.mode, "FAILED: Incorrect Localization type used in the method");
    }

    private EditorPage saveChanges(){
        saveButton.click();
        waitSaving();
        return new EditorPage(this.mode);
    }

    public void switchTo(LocalizationMode mode){
        switch (mode){
            case COPY:
                copyButton.click();
                setLocalizationSidebar(COPY);
                break;
            case COLOR:
                colorButton.click();
                setLocalizationSidebar(COLOR);
                break;
            case IMAGES:
                imagesButton.click();
                setLocalizationSidebar(IMAGES);
                break;
            case CONFIGURATION:
                configurationButton.click();
                setLocalizationSidebar(CONFIGURATION);
                break;
            default:
                Assert.fail("FAILED: Unknown localization type: <" + mode + ">");
                break;
        }
    }

    public void clickCreateABTest(LocalizationMode type, String localizationName){
        localizationSidebar.getRecord(type, localizationName).createABTest();
        //TODO: Return action to be added after implementation of AB Test editor page
    }

    public void clickCopyToOtherCampaigns(LocalizationMode type, String localizationName){
        localizationSidebar.getRecord(type, localizationName).copyToOtherCampaigns();
        //TODO: Return action to be added after implementation of Multi-campaign editor page
    }
}
