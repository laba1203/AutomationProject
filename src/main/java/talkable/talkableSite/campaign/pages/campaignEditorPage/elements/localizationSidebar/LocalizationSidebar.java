package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LocalizationSidebar extends AbstractElementsContainer{

    private static final String localizationItemsXpath = "//div[contains(@class, 'Locale-entries-localizations-item')]";

    ElementCopyButton copyButton;
    ElementImagesButton imagesButton;
    ElmntColorButton colorButton;
    ElementConfigurationButton configurationButton;

    ArrayList<LocalizationRecord> records = new ArrayList<>();

    public LocalizationSidebar(){
        copyButton = new ElementCopyButton();
        imagesButton = new ElementImagesButton();
        colorButton = new ElmntColorButton();
        configurationButton = new ElementConfigurationButton();

        setRecords();

    }

    private void setRecords(){
        List<WebElement> items = driver.findElements(By.xpath(localizationItemsXpath));
        for (WebElement webElement :
                items) {
            records.add(new LocalizationRecord(webElement));
        }
    }

    public void setCopyLocalization(String localizationName, String newValue){
        LocalizationRecord record = getRecordByName(localizationName);
        record.value.click();
        record.value.clear();
        record.value.sendKeys(newValue);
    }

    public LocalizationRecord getRecordByName(String localizationName){
        for (LocalizationRecord record : records) {
            if(record.name.getText().equals(localizationName)){
                return  record;
            }
        }
        Assert.fail("FAILED: There no view records with name : <" + localizationName + ">");
        return null;
    }







}
