package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LocalizationCopyRecord extends AbstractElementsContainer{

    Element name;
    Element value;
    Element createABTestButton;
    Element copyToOtherCampaignsBtn;

    LocalizationCopyRecord(WebElement webElement){
        name = new Element(webElement.findElement(By.xpath("./div[contains(@class, 'localizations-human-name')]")));
        value = new Element(webElement.findElement(By.xpath(".//textarea")));
        createABTestButton = new Element(webElement.findElement(By.xpath(".//a[@data-action = 'Create A/B test variant']")));
        copyToOtherCampaignsBtn = new Element(webElement.findElement(By.xpath(".//a[@data-label = 'Copy to other campaigns']")));
    }

    public Element getName() {
        return name;
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
