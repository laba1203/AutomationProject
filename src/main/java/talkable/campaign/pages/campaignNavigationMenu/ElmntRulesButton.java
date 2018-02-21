package talkable.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntRulesButton extends AbstractElement{
    private static final By locator = By.linkText("Rules");

    ElmntRulesButton(){
        setWebElement(locator);
    }
}
