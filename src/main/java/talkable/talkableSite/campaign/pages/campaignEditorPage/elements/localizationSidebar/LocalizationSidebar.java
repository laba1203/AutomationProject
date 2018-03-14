package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.campaignEditorPage.EditorPage;
import java.util.ArrayList;
import java.util.List;

import static talkable.talkableSite.campaign.pages.campaignEditorPage.EditorPage.LocalizationMode.*;

public class LocalizationSidebar extends AbstractElementsContainer{

    private static final String localizationItemsXpath = "//div[contains(@class, 'Locale-entries-localizations-item')]";
    private EditorPage.LocalizationMode mode;
    private ArrayList<LocalizationCopyRecord> copyRecords = new ArrayList<>();
    private ArrayList<LocalizationColorRecord> colorRecords = new ArrayList<>();
    private ArrayList<LocalizationImagesRecord> imagesRecords = new ArrayList<>();
    private ArrayList<LocalizationConfigRecord> configRecords = new ArrayList<>();


    public LocalizationSidebar(EditorPage.LocalizationMode mode){
        setRecords(mode);
        this.mode = mode;
    }

    private void setRecords(EditorPage.LocalizationMode mode){
        List<WebElement> items = driver.findElements(By.xpath(localizationItemsXpath));
        for (WebElement webElement :
                items) {
            addRecord(mode, webElement);
        }
    }

    public void setCopyLocalization(String localizationName, String newValue){
        LocalizationCopyRecord record = getCopyRecordByName(localizationName);
        record.value.click();
        record.value.clear();
        record.value.sendKeys(newValue);
    }



    public LocalizationCopyRecord getCopyRecordByName(String localizationName){
        verifyMode(COPY);
        for (LocalizationCopyRecord record : copyRecords) {
            if(record.name.getText().equals(localizationName)){
                return  record;
            }
        }
        Assert.fail("FAILED: There no view copyRecords with name : <" + localizationName + ">");
        return null;
    }

    public LocalizationColorRecord getColorRecordByName(String localizationName){
        verifyMode(COLOR);
        for (LocalizationColorRecord record : colorRecords) {
            if(record.getNameText().equals(localizationName)){
                return record;
            }
        }
        Assert.fail("FAILED: There no view copyRecords with name : <" + localizationName + ">");
        return null;
    }

    public LocalizationImagesRecord getImagesRecordByName(String localizationName){
        verifyMode(IMAGES);
        for (LocalizationImagesRecord record : imagesRecords) {
            if(record.getNameText().equals(localizationName)){
                return record;
            }
        }
        Assert.fail("FAILED: There no view copyRecords with name : <" + localizationName + ">");
        return null;
    }

    public LocalizationConfigRecord getConfigRecordByName(String localizationName){
        verifyMode(CONFIGURATION);
        for (LocalizationConfigRecord record : configRecords) {
            if(record.getNameText().equals(localizationName)){
                return record;
            }
        }
        Assert.fail("FAILED: There no view copyRecords with name : <" + localizationName + ">");
        return null;
    }


    private void verifyMode(EditorPage.LocalizationMode actualMode){
        Assert.assertEquals(actualMode, this.mode, "FAILED: Incorrect localization mode");
    }


    private void addRecord(EditorPage.LocalizationMode mode, WebElement webElement){
        switch (mode){
            case COPY:
                copyRecords.add(new LocalizationCopyRecord(webElement));
                break;
            case COLOR:
                colorRecords.add(new LocalizationColorRecord(webElement));
                break;
            case IMAGES:
                imagesRecords.add(new LocalizationImagesRecord(webElement));
                break;
            case CONFIGURATION:
                configRecords.add(new LocalizationConfigRecord(webElement));
                break;
            default:
                Assert.fail("FAILED: Unknown localization mode: <" + mode.toString() + ">");
                break;
        }
    }



}
