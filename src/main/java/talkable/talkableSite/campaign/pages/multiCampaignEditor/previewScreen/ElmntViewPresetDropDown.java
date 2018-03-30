package talkable.talkableSite.campaign.pages.multiCampaignEditor.previewScreen;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

public class ElmntViewPresetDropDown extends AbstractSelectElement{

    private static final By locator = By.xpath("//div[@class='modal-header']//label[contains(text(), 'View preset')]/../select");

    ElmntViewPresetDropDown(){
        setWebElement(locator);
    }
}
