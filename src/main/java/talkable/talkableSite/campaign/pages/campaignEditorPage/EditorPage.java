package talkable.talkableSite.campaign.pages.campaignEditorPage;

import org.testng.Assert;
import talkable.talkableSite.AbstractTkblSitePageWithoutHeader;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar.LocalizationSidebar;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;

import static talkable.talkableSite.campaign.pages.campaignEditorPage.EditorPage.LocalizationMode.*;

public class EditorPage extends AbstractTkblSitePageWithoutHeader{

    public CampaignNavigationMenuOnEditor campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
    public LocalizationSidebar localizationSidebar;
    private ElmntSaveButton saveButton = new ElmntSaveButton();
    private ElmntSelectedViewField elmntSelectedViewField = new ElmntSelectedViewField();
    private ElmntCopyButton copyButton = new ElmntCopyButton();
    private ElmntImagesButton imagesButton = new ElmntImagesButton();
    private ElmntColorButton colorButton = new ElmntColorButton();
    private ElmntConfigurationButton configurationButton = new ElmntConfigurationButton();

    public enum LocalizationMode{COPY, IMAGES, COLOR, CONFIGURATION}
    private LocalizationMode mode;

    public EditorPage(){
        localizationSidebar = new LocalizationSidebar(COPY);
        mode = COPY;
    }

    private EditorPage(LocalizationMode mode){
        localizationSidebar = new LocalizationSidebar(mode);
        this.mode = mode;
    }

    public EditorPage switchViewByIndex(int index){
        elmntSelectedViewField.click();
        new ContainerViewRecords().selectByIndex(index);
        return new EditorPage();
    }

    public EditorPage switchViewByName(String name){
        elmntSelectedViewField.click();
        new ContainerViewRecords().selectViewByText(name);
        return new EditorPage();
    }

    public EditorPage updateLocalization(LocalizationMode type, String localizationName, String newValue){
        verifyLocalizationMode(type);
        localizationSidebar.updateRecord(type, localizationName, newValue);
        saveChanges();
        return new EditorPage(type);
    }

    private void verifyLocalizationMode(LocalizationMode mode){
        Assert.assertEquals(mode, this.mode, "FAILED: Incorrect Localization type used in the method");
    }

    private EditorPage saveChanges(){
        saveButton.click();
        waitSaving();
        return new EditorPage(this.mode);
    }

    public EditorPage switchToCopyTab(){
        copyButton.click();
        return new EditorPage(COPY);
    }

    public EditorPage switchToImagesTab(){
        imagesButton.click();
        return new EditorPage(IMAGES);
    }

    public EditorPage switchToColorTab(){
        colorButton.click();
        return new EditorPage(COLOR);
    }

    public EditorPage switchToConfigTab(){
        configurationButton.click();
        return new EditorPage(CONFIGURATION);
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
