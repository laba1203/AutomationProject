package talkable.campaign.pages.campaignNavigationMenu.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class EditorButton extends AbstractElement{
    private static final By locator = By.linkText("Editor");

    public EditorButton(){
        setWebElement(locator);
    }
}
