package talkable.talkableSite.campaign.pages.editorPage.localizationSidebar;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class LocalizationColorRecord extends AbstractLocalizationRecord{

    private Element colorValueInput;


    LocalizationColorRecord(WebElement webElement){
        super(webElement);
        colorValueInput = new Element(webElement.findElement(By.xpath(".//*[contains(@class , 'asColorPicker-input')]")));
    }

    @Override
    public void update(String newValue) {
        colorValueInput.clear();
        colorValueInput.sendKeys(newValue);
    }

    @Override
    public String getValueText() {
        return colorValueInput.getAttribute("value");
    }
}
