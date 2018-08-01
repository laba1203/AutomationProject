package talkable.talkableSite.campaign.pages.editorPage.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;

import java.util.ArrayList;
import java.util.List;

public class LocalizationSidebar extends AbstractElementsContainer{

    private static final String localizationItemsXpath = "//div[contains(@class, 'Locale-entries-localizations-item')]";
    private static final By firstRecordLctr = By.xpath("//div[@class='Locale-entries-localizations-list']/div[contains(@class, 'Locale-entries-localizations-item')][1]");
    private static final By searchCustomSettingsLctr = By.xpath("//input[@placeholder = 'Search Custom Settings']");
    private SimpleEditorPage.LocalizationType mode;

    private ArrayList<RecordFactory> records = new ArrayList<>();
    private Element searchCustomSettingsField = new Element(searchCustomSettingsLctr, "Search Custom Settings field");

    public LocalizationSidebar(SimpleEditorPage.LocalizationType mode){
        this.mode = mode;
        setRecords(mode);
    }

    public RecordFactory getFirstRecord(){
        return createNewRecord(mode, firstRecordLctr);
    }

    private void setRecords(SimpleEditorPage.LocalizationType mode){
        verifyMode(mode);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(localizationItemsXpath)));
        List<WebElement> items = driver.findElements(By.xpath(localizationItemsXpath));
        for (WebElement webElement :
                items) {
            records.add(createNewRecord(mode, webElement));
        }
    }

    public SimpleEditorPage search(String name, SimpleEditorPage.LocalizationType mode){
        searchCustomSettingsField.sendKeys(name);
        return new SimpleEditorPage(mode);
    }


    public void updateRecord(String localizationName, String newValue){
        findRecord(records, localizationName)
                .update(newValue);
    }

    public RecordFactory getRecord(String localeName){
        return findRecord(records, localeName);
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

    private void verifyMode(SimpleEditorPage.LocalizationType actualMode){
        Assert.assertEquals(actualMode, this.mode, "FAILED: Incorrect localization mode");
    }

    private void assetFail(String localizationName){
        Assert.fail("FAILED: There is no view copyRecords with name : <" + localizationName + ">");
    }

    private RecordFactory createNewRecord(SimpleEditorPage.LocalizationType mode, By locator){
        WebElement parentWebElement = driver.findElement(locator);
        return createNewRecord(mode, parentWebElement);
    }

    private RecordFactory createNewRecord(SimpleEditorPage.LocalizationType mode, WebElement webElement){
        switch (mode){
            case COPY:
                return new LocalizationCopyRecord(webElement);
            case COLOR:
                return new LocalizationColorRecord(webElement);
            case IMAGES:
                return new LocalizationImagesRecord(webElement);
            case CONFIGURATION:
                return new LocalizationConfigRecord(webElement);
            default:
                Assert.fail("Unknown localization mode:" + mode);
                return null;
        }
    }
}
