package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class ElmntAdOfferDeadlineHours extends AbstractSelectElement{

    ElmntAdOfferDeadlineHours(){

        WebElement webElement = getDriver().findElement(By.xpath("//select[@name='expires_at.hour']"));
        setWebElement(webElement);
    }
}
