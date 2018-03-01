package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

class ElmntLoadingMessage extends AbstractSelectElement{
    private static final By locator = By.cssSelector(".loadingMessage");

    ElmntLoadingMessage(){
        setWebElement(locator);
    }
}
