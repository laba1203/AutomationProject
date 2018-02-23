package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

class ElmntFrOfferDeadlineMinutes extends AbstractSelectElement{
    private static final By locator = By.cssSelector("//select[@name='friend_deadline.minute']]");

    ElmntFrOfferDeadlineMinutes(){
        setWebElement("//select[@name='friend_deadline.minute']");
    }
}
