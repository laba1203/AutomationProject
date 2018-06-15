package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntNonMonetaryRewardTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.cssSelector("label[for = 'other']");

    ElmntNonMonetaryRewardTypeRadiobutton(){
        setWebElement(locator);
    }
}
