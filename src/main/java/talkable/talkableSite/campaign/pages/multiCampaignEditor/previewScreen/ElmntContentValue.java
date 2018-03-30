package talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntContentValue extends AbstractElement{
    private static final By locator = By.xpath("//*[@class='abtest-preview-variant-value']/div[2]");

    ElmntContentValue(){
        setWebElement(locator);
    }
}
