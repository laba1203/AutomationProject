package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

class ElmntIncentiveTypeItem extends AbstractSelectElement{
    private static final By locator = By.cssSelector(".Rules-incentives-dropdown a>div:nth-of-type(1)");

    ElmntIncentiveTypeItem(){
        setWebElement(locator);
    }

    @Override
    public ArrayList<WebElement> getWebElements(By locator) {
        return super.getWebElements(locator);
    }
}
