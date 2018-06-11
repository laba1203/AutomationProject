package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntMultiUseRadiobutton extends AbstractElement{
    private static final  By locator = By.xpath("//label[@for='multi']");

    ElmntMultiUseRadiobutton(){
        setWebElement(locator);
    }
}
