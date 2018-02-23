package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElementCopyButton extends AbstractElement{
    private By locator = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(1)");

    ElementCopyButton(){
        setWebElement(locator);
    }
}
