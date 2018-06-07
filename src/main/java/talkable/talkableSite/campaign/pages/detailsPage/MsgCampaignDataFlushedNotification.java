package talkable.talkableSite.campaign.pages.detailsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class MsgCampaignDataFlushedNotification extends AbstractElement{
    private static final By locator = By.xpath("//div[contains(text() ,'Campaign data was successfully flushed')]");

    MsgCampaignDataFlushedNotification(){
        setWebElement(locator);
    }

}
