package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntRewardAmountInput extends AbstractElement{
    private static final  By locator = By.cssSelector("input[name = 'incentives.amount']");

    ElmntRewardAmountInput(){
        setWebElement(locator);
    }
}
