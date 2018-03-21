package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCampaignFilter extends AbstractElement{
    private static final By locator = By.xpath("//*[contains(@class, 'campaignfilter')]/input");

    ElmntCampaignFilter(){
        setWebElement(locator);
    }

}
