package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

class ElmntAdOfferDeadlineHours extends AbstractSelectElement{
    private static final By locator = By.cssSelector("//select[@name='expires_at.hour']");

    ElmntAdOfferDeadlineHours(){
        setWebElement("//select[@name='expires_at.hour']");
    }
}
