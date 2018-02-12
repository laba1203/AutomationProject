package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

class ElmntFrOfferDeadlineHours extends AbstractSelectElement{
    private static final By locator = By.cssSelector("//select[@name='friend_deadline.hour']");

    ElmntFrOfferDeadlineHours(){
        setWebElement("//select[@name='friend_deadline.hour']");
    }
}
