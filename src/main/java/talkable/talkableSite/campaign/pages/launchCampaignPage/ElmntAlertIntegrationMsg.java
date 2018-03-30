package talkable.talkableSite.campaign.pages.launchCampaignPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAlertIntegrationMsg extends AbstractElement{
    private static final By locator = By.xpath("//p[contains(text(), 'Your site is not yet integrated')]");

    ElmntAlertIntegrationMsg(){
        setWebElement(locator);
    }

    static By getStaticLocator(){
        return locator;
    }
}
