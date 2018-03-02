package talkable.talkableSite.campaign.pages.launchCampaignPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAlertIntegrationMsg extends AbstractElement{
    private static final By locator = By.cssSelector(".alert-info>.container");

    ElmntAlertIntegrationMsg(){
        setWebElement(locator);
    }

    static By getStaticLocator(){
        return locator;
    }
}
