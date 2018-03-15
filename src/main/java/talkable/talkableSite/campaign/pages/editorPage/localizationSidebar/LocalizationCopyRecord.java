package talkable.talkableSite.campaign.pages.editorPage.localizationSidebar;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LocalizationCopyRecord extends AbstractLocalizationRecord{

    private Element value;

    LocalizationCopyRecord(WebElement webElement){
        super(webElement);
        value = new Element(webElement.findElement(By.xpath(".//textarea")));
    }

    public Element getValue() {
        return value;
    }

    @Override
    public void update(String text){
        value.click();
        value.clear();
        value.sendKeys(text);
    }

    @Override
    public String getValueText() {
        return value.getText();
    }
}
