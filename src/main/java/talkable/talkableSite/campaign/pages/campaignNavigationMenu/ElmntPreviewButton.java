package talkable.talkableSite.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntPreviewButton extends AbstractElement{
    private static final By locator = By.linkText("Preview");

    ElmntPreviewButton(){
        setWebElement(locator);
    }
}
