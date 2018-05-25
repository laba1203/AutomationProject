package talkable.talkableSite.campaign.pages.editorPage.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import java.util.ArrayList;
import java.util.List;

public class LocalizationSidebar extends AbstractElementsContainer{

    private static final String localizationItemsXpath = "//div[contains(@class, 'Locale-entries-localizations-item')]";
    private EditorPage.LocalizationType mode;
    private ArrayList<RecordFactory> copyRecords = new ArrayList<>();
    private ArrayList<RecordFactory> colorRecords = new ArrayList<>();
    private ArrayList<RecordFactory> imagesRecords = new ArrayList<>();
    private ArrayList<RecordFactory> configRecords = new ArrayList<>();

    public LocalizationSidebar(EditorPage.LocalizationType mode){
        setRecords(mode);
        this.mode = mode;
    }

    private void setRecords(EditorPage.LocalizationType mode){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(localizationItemsXpath)));
        List<WebElement> items = driver.findElements(By.xpath(localizationItemsXpath));
        for (WebElement webElement :
                items) {
            addRecord(mode, webElement);
        }
    }


    public void updateRecord(EditorPage.LocalizationType mode, String localizationName, String newValue){
        getRecord(mode, localizationName).update(newValue);
    }

    public RecordFactory getRecord(EditorPage.LocalizationType mode, String localizationName){
        verifyMode(mode);
        switch (mode){
            case CONFIGURATION:
                return findRecord(configRecords, localizationName);
            case IMAGES:
                return findRecord(imagesRecords, localizationName);
            case COLOR:
                return findRecord(colorRecords, localizationName);
            case COPY:
                return findRecord(copyRecords, localizationName);
            default:
                assetFail(localizationName);
                return null;
        }
    }

    private RecordFactory findRecord(ArrayList<RecordFactory> records, String recordName){
        for (RecordFactory record : records) {
            if(record.getNameText().equals(recordName)){
                return record;
            }
        }
        assetFail(recordName);
        return null;
    }

    private void verifyMode(EditorPage.LocalizationType actualMode){
        Assert.assertEquals(actualMode, this.mode, "FAILED: Incorrect localization mode");
    }

    private void assetFail(String localizationName){
        Assert.fail("FAILED: There is no view copyRecords with name : <" + localizationName + ">");
    }

    private void addRecord(EditorPage.LocalizationType mode, WebElement webElement){
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
