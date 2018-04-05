package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElmntIncentiveTypeItem extends AbstractElement{
    private static final By locator = By.cssSelector(".Rules-incentives-dropdown a>div:nth-of-type(1)");

    public ElmntIncentiveTypeItem(){
        setWebElement(locator);
//        WebElement webElement = getDriver().findElement(By.cssSelector(".Rules-incentives-dropdown a>div:nth-of-type(1)"));
//        setWebElement(webElement);
    }

}
