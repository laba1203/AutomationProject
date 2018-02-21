package talkable.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntEditorButton extends AbstractElement{
    private static final By locator = By.linkText("Editor");

    ElmntEditorButton(){
        setWebElement(locator);
    }
}
