package talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

class ElmntCampaignsDropDown extends AbstractSelectElement{

    private static final By locator = By.xpath("//div[@class='modal-header']//label[contains(text(), 'Campaign')]/../select");

    ElmntCampaignsDropDown(){
        setWebElement(locator);
    }
}
