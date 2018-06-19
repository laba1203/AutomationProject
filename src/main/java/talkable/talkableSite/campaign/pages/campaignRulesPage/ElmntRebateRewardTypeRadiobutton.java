package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntRebateRewardTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.cssSelector("label[for = 'rebate']");

    ElmntRebateRewardTypeRadiobutton(){
        setWebElement(locator);
    }
}
