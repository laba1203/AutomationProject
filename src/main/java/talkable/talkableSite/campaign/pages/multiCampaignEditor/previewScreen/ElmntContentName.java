package talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntContentName extends AbstractElement{
    private static final By locator = By.xpath("//*[@class='abtest-preview-variant-value']//strong");

    ElmntContentName(){
        setWebElement(locator);
    }
}
