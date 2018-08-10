package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.ab.tests.newAbTest.AbTestPage;
import talkable.talkableSite.campaign.pages.editorPage.localizationSidebar.LocalizationSidebar;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.PageMultiCampaignEditor;
import util.logging.Log;

import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.*;

public class SimpleEditorPage extends AbstractEditorPage{

    private static final By selectedLocalizationModeLctr = By.xpath("//div[contains( @class, 'localizations-filters')]/div[contains(@class, 'is-active')]/span");
    private static final By copyBtnLctr = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(1)");
    private static final By imageBtnLCtr = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(2)");
    private static final By colorBtnLctr = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(3)");
    private static final By configurationBtnLctr = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(4)");



    public CampaignNavigationMenuOnEditor campaignNavigationMenu = new CampaignNavigationMenuOnEditor();

    private Element copyButton = new Element(copyBtnLctr, "COPY button");
    private Element imagesButton = new Element(imageBtnLCtr, "IMAGE button");
    private Element colorButton = new Element(colorBtnLctr, "COLOR button");
    private Element configurationButton = new Element(configurationBtnLctr, "CONFIGURATION button");

    public enum LocalizationType {COPY, IMAGES, COLOR, CONFIGURATION}
    private LocalizationType mode = COPY; //COPY is a default mode;

    public SimpleEditorPage(){
        Log.debagRecord("Initiated Simple Editor with default mode: " + getMode());
    }

    public SimpleEditorPage(LocalizationType mode){
        switchTo(mode);
    }

    private void setMode(LocalizationType mode){
        this.mode = mode;
    }

    private LocalizationType getMode(){
        return mode;
    }


    public SimpleEditorPage switchViewByNameOnSimpleEditor(String name){
        switchViewByName(name);
        return new SimpleEditorPage(getMode());
    }

     public SimpleEditorPage deleteViewPreset(String presetName) {
        deletePresetOnSimpleEditor(presetName);
        return new SimpleEditorPage(getMode());
     }

    private boolean isViewSelected(String toBeSelected){
        return elmntSelectedViewField.getText().equals(toBeSelected);
    }

    public SimpleEditorPage updateLocalization(LocalizationType type, String localizationName, String newValue){
        verifyLocalizationMode(type);
        getLocalizationSidebar().updateRecord(localizationName, newValue);
        return saveChangesInSimpleEditor();
    }

    private LocalizationSidebar getLocalizationSidebar(){
        return new LocalizationSidebar(getMode());
    }

    public SimpleEditorPage searchLocale(String name, LocalizationType mode){
        switchTo(mode);
        return getLocalizationSidebar().search(name, mode);
    }

    public String getFirstLocaleName(){
        return getLocalizationSidebar()
                .getFirstRecord()
                .getNameText();
    }

    public AbTestTileInEditor getFirstLocaleWithAbTest(){
        return getLocalizationSidebar()
                .getFirstLocaleWithAbTest();
    }


    public String getLocalizationValue(String localizationName){
        return getLocalizationSidebar()
                .getRecord(localizationName)
                .getValueText();
    }

    private void verifyLocalizationMode(LocalizationType mode){
        Assert.assertEquals(mode, getMode(), "FAILED: Incorrect Localization type used in the method");
    }

    private SimpleEditorPage saveChangesInSimpleEditor(){
        saveChanges();
        return new SimpleEditorPage(getMode());
    }

    public SimpleEditorPage switchTo(LocalizationType mode){
        setMode(mode);
        if(isLocalizationTabAlreadySelected(mode)){
            Log.logRecord("Localization tab <" + mode + "> is already selected.");
            return this;
        } else {
            switch (mode) {
                case COPY:
                    wait.until(ExpectedConditions.elementToBeClickable(copyButton.getWebElement()));
                    copyButton.moveToElementAndClick();
                    break;
                case COLOR:
                    wait.until(ExpectedConditions.elementToBeClickable(colorButton.getWebElement()));
                    colorButton.moveToElementAndClick();
                    break;
                case IMAGES:
                    wait.until(ExpectedConditions.elementToBeClickable(imagesButton.getWebElement()));
                    imagesButton.moveToElementAndClick();
                    break;
                case CONFIGURATION:
                    wait.until(ExpectedConditions.elementToBeClickable(configurationButton.getWebElement()));
                    configurationButton.moveToElementAndClick();
                    break;
                default:
                    Assert.fail("FAILED: Unknown localization type: <" + mode + ">");
                    break;
            }
            getLocalizationSidebar();
            return this; //DON'T USE return new SimpleEditorPage(mode); - it will prevent to infinity loop!
        }
    }

    public PageMultiCampaignEditor clickCopyToOtherCampaigns(LocalizationType type, String localizationName){
        getLocalizationSidebar().getRecord(localizationName).copyToOtherCampaigns();
        return new PageMultiCampaignEditor(getMode());
    }

    private boolean isLocalizationTabAlreadySelected(LocalizationType expectedMode){
        return expectedMode.equals(getSelectedLocalizationMode());
    }

    private LocalizationType getSelectedLocalizationMode(){
        String selectedTab = new Element(selectedLocalizationModeLctr).getText();
        switch (selectedTab){
            case "Copy":
                return COPY;
            case "Images":
                return IMAGES;
            case "Color":
                return COLOR;
            case "Configuration":
                return CONFIGURATION;
            default:
                Assert.fail("Unknown text in the selected Localization tab <" + selectedTab + ">. It doesn't match to any Localization mode.");
                return null;
        }
    }

    public AbTestPage clickCreateABTest(String localizationName){
        getLocalizationSidebar().getRecord(localizationName).createABTest();
        return new AbTestPage();
    }
}
