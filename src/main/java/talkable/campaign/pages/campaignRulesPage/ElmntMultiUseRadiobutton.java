package talkable.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntMultiUseRadiobutton extends AbstractElement{
    private static final  By locator = By.cssSelector("input[value='multi-use']");

    ElmntMultiUseRadiobutton(){
        setWebElement(locator);
    }
}
