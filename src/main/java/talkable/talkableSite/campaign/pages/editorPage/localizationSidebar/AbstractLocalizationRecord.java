package talkable.talkableSite.campaign.pages.editorPage.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.DrivenElement;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

abstract class AbstractLocalizationRecord extends AbstractElementsContainer implements RecordFactory{

    private static final By copyToOtherBtnLctr = By.xpath(".//a[@data-label = 'Copy to other campaigns']");

    private WebElement webElement;
//    private Element localizationDiv; //element of div with localization items. This is required for scrolling option
    protected Element name;
    protected Element createABTestButton;
    protected Element copyToOtherCampaignsBtn;



    AbstractLocalizationRecord(WebElement webElement){
        this.webElement = webElement;
        name = new Element(webElement.findElement(By.xpath("./div[contains(@class, 'localizations-human-name')]")));
        createABTestButton = new Element(webElement.findElement(By.xpath(".//a[@data-action = 'Create A/B test variant']")));
        copyToOtherCampaignsBtn = new Element(webElement.findElement(copyToOtherBtnLctr), "'Copy to other campaigns' button");
        //
//        localizationDiv = new Element(driver.findElement(By.xpath("//div[contains(@class, 'Locale-entries-sidebar-localizations-wrapper')]")));
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

    protected WebElement getWebElement(){
        return webElement;
    }

}
