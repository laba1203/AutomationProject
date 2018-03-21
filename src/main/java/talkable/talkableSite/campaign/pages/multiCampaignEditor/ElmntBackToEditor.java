package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntBackToEditor extends AbstractElement{
    private static final By locator = By.xpath("//*[contains(text(), 'Back to Editor')]/..");

    ElmntBackToEditor(){
        setWebElement(locator);
    }
}
