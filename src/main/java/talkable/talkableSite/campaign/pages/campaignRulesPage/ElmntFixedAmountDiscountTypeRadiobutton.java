package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntFixedAmountDiscountTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.xpath("//button[text() = '$']");

    ElmntFixedAmountDiscountTypeRadiobutton(){
        setWebElement(locator);
    }
}
