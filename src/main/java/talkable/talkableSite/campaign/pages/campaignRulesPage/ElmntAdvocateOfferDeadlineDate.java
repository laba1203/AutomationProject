package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAdvocateOfferDeadlineDate extends AbstractElement{

    private static final By locator = By.cssSelector("[name = 'expires_at.date']");

    ElmntAdvocateOfferDeadlineDate(){
        setWebElement(locator);
    }

}
