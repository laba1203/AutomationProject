package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntRewardAmountInput extends AbstractElement{
    private static final  By locator = By.xpath("//input[@name = 'amount']");

    ElmntRewardAmountInput(){
        setWebElement(locator);
    }
}
