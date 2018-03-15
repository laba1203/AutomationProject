package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract class AbstractLocalizationRecord extends AbstractElementsContainer implements RecordFactory{

    protected Element name;
    protected Element createABTestButton;
    protected Element copyToOtherCampaignsBtn;

    AbstractLocalizationRecord(WebElement webElement){
        name = new Element(webElement.findElement(By.xpath("./div[contains(@class, 'localizations-human-name')]")));
        createABTestButton = new Element(webElement.findElement(By.xpath(".//a[@data-action = 'Create A/B test variant']")));
        copyToOtherCampaignsBtn = new Element(webElement.findElement(By.xpath(".//a[@data-label = 'Copy to other campaigns']")));
    }

    @Override
    public String getNameText(){
        return name.getText();
    }

    @Override
    public void createABTest(){
        createABTestButton.click();
    }

    @Override
    public void copyToOtherCampaigns(){
        copyToOtherCampaignsBtn.click();
    }

}
