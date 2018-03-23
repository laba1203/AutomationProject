package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntContentField extends AbstractElement{
    private static final By locator = By.xpath("//*[contains(text(), 'Content')]/../p");

    ElmntContentField(){
        setWebElement(locator);
    }
}
