package talkable.talkableSite.campaign.pages.editorPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.AbstractTkblSitePageWithoutHeader;
import talkable.talkableSite.campaign.pages.editorPage.localizationSidebar.LocalizationSidebar;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;

import static talkable.talkableSite.campaign.pages.editorPage.EditorPage.LocalizationType.*;

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

    public enum LocalizationType {COPY, IMAGES, COLOR, CONFIGURATION}
    private LocalizationType mode;

    public EditorPage(){
        switchTo(COPY);
        setLocalizationSidebar(COPY);
    }

    private void setLocalizationSidebar(LocalizationType mode){
        localizationSidebar = new LocalizationSidebar(mode);
        this.mode = mode;
    }

    public EditorPage(LocalizationType mode){
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
        if(isViewSelected(name)) {
            System.out.println("DEBAG: View <" + name + "> is already selected");
        }else{
            elmntSelectedViewField.click();
            new ContainerViewRecords().selectViewByText(name);
            System.out.println("DEBAG: View changed to : " + name);
        }

        return new EditorPage(this.mode);
    }

    private boolean isViewSelected(String toBeSelected){
        return elmntSelectedViewField.getText().equals(toBeSelected);
    }

    public EditorPage updateLocalization(LocalizationType type, String localizationName, String newValue){
        verifyLocalizationMode(type);
        localizationSidebar.updateRecord(type, localizationName, newValue);
        saveChanges();
        return new EditorPage(type);
    }


    public String getLocalizationValue(LocalizationType type, String localizationName){
        return localizationSidebar.getRecord(type, localizationName).getValueText();
    }

    private void verifyLocalizationMode(LocalizationType mode){
        Assert.assertEquals(mode, this.mode, "FAILED: Incorrect Localization type used in the method");
    }

    private EditorPage saveChanges(){
        saveButton.click();
        waitSaving();
        return new EditorPage(this.mode);
    }

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
