package talkable.talkableSite.campaignsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class MsgCampaignDeactivated extends AbstractElement{
    private static final By locator = By.xpath("//div[contains(text(), 'campaign has been deactivated')]");

    MsgCampaignDeactivated(){
        setWebElement(locator);
    }
}
