package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class ElmntFrOfferDeadlineHours extends AbstractSelectElement{

    ElmntFrOfferDeadlineHours(){
        WebElement webElement = getDriver().findElement(By.xpath("//select[@name='expires_at.hour']"));
        setWebElement(webElement);
    }
}
