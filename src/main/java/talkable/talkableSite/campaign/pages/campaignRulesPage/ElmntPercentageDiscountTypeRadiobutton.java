package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntPercentageDiscountTypeRadiobutton extends AbstractElement{
    private static final  By locator = By.xpath("//button[text() = '%']");

    ElmntPercentageDiscountTypeRadiobutton(){
        setWebElement(locator);
    }
}
