package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntFriendOfferDeadlineDate extends AbstractElement{

    private static final By locator = By.cssSelector("[name = 'friend_deadline.date']");

    ElmntFriendOfferDeadlineDate(){
        setWebElement(locator);
    }

}
