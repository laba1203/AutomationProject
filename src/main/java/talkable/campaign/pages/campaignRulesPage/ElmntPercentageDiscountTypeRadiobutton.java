package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntPercentageDiscountTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.cssSelector("input[name = 'discount_type'][value='true']");

    ElmntPercentageDiscountTypeRadiobutton(){
        setWebElement(locator);
    }
}
