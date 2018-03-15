package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntLoadingMessage extends AbstractElement{
    private static final By locator = By.cssSelector(".loadingMessage");

    ElmntLoadingMessage(){
        setWebElement(locator);
    }
}
