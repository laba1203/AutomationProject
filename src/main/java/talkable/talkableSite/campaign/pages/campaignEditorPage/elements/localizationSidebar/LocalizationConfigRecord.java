package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.SelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class LocalizationConfigRecord extends AbstractLocalizationRecord{

    private SelectElement dropdown;

    LocalizationConfigRecord(WebElement webElement){
        super(webElement);
        dropdown = new SelectElement(webElement.findElement(By.xpath(".//select")));
    }

    @Override
    public void update(String newValue) {
        dropdown.selectByVisibleText(newValue);
    }

    @Override
    public String getValueText() {
        return dropdown.getSelectedItemText();
    }
}
