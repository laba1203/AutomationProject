package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class LocalizationColorRecord extends AbstractLocalizationRecord{

    Element colorValueInput;


    LocalizationColorRecord(WebElement webElement){
        super(webElement);
        colorValueInput = new Element(webElement.findElement(By.xpath(".//*[contains(@class , 'asColorPicker-input')]")));
    }


}
