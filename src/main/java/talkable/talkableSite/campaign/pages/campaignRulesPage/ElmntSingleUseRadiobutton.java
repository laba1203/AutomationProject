package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSingleUseRadiobutton extends AbstractElement{
    private static final  By locator = By.xpath("//label[@for='single']");

    ElmntSingleUseRadiobutton(){
        setWebElement(locator);
    }
}
