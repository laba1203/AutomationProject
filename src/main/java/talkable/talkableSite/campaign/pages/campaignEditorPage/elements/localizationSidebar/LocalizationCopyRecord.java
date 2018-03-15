package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LocalizationCopyRecord extends AbstractLocalizationRecord{

//    Element name;
    Element value;
//    Element createABTestButton;
//    Element copyToOtherCampaignsBtn;

    LocalizationCopyRecord(WebElement webElement){
        super(webElement);

        value = new Element(webElement.findElement(By.xpath(".//textarea")));
//        createABTestButton = new Element(webElement.findElement(By.xpath(".//a[@data-action = 'Create A/B test variant']")));
//        copyToOtherCampaignsBtn = new Element(webElement.findElement(By.xpath(".//a[@data-label = 'Copy to other campaigns']")));
    }

    public Element getValue() {
        return value;
    }

    public void sendKeys(String text){
        value.click();
        value.clear();
        value.sendKeys(text);
    }
}
