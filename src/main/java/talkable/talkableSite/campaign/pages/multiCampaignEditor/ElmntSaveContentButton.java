package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSaveContentButton extends AbstractElement{
    private static final By locator = By.xpath("//*[contains(text(), 'Save Content')]");

    ElmntSaveContentButton(){
        setWebElement(locator);
    }
}
