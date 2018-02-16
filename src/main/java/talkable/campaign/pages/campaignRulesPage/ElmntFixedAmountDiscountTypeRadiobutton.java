package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntFixedAmountDiscountTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.cssSelector("input[name = 'discount_type'][value='false']");

    ElmntFixedAmountDiscountTypeRadiobutton(){
        setWebElement(locator);
    }
}
