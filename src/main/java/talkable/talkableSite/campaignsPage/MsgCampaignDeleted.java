package talkable.talkableSite.campaignsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class MsgCampaignDeleted extends AbstractElement{
    private static final By locator = By.xpath("//div[contains(text(), 'Campaign deleted')]");

    MsgCampaignDeleted(){
        setWebElement(locator);
    }
}
