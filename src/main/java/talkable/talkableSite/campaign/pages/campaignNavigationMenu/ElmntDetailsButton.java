package talkable.talkableSite.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntDetailsButton extends AbstractElement{
    private static final By locator = By.linkText("Details");

    ElmntDetailsButton(){
        setWebElement(locator);
    }
}
