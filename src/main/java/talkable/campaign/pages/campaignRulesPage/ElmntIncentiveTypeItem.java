package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

public class ElmntIncentiveTypeItem extends AbstractSelectElement{
    private static final By locator = By.cssSelector(".Rules-incentives-dropdown a>div:nth-of-type(1)");

    public ElmntIncentiveTypeItem(){
        setWebElement(locator);
    }

}
