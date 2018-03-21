package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignViewField extends AbstractElement{
    private static final By locator = By.xpath("//*[contains(text(), 'Campaign view')]/../p");

    ElmntCampaignViewField(){
        setWebElement(locator);
    }
}
