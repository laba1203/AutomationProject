package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

class ElmntAdOfferDeadlineMinutes extends AbstractSelectElement{
    private static final By locator = By.cssSelector("//select[@name='expires_at.minute']");

    ElmntAdOfferDeadlineMinutes(){
        setWebElement("//select[@name='expires_at.minute']");
    }
}
