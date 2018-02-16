package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntRebateRewardTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.cssSelector("input[value = 'rebate']");

    ElmntRebateRewardTypeRadiobutton(){
        setWebElement(locator);
    }
}
