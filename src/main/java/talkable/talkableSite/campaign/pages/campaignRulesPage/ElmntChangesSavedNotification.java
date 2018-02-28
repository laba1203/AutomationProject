package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

class ElmntChangesSavedNotification extends AbstractSelectElement{
    private static final By locator = By.xpath("//div[text()='Changes have been saved']");

    ElmntChangesSavedNotification(){
        setWebElement(locator);
    }
}
