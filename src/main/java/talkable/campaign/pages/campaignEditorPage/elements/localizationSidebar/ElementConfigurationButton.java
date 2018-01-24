package talkable.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElementConfigurationButton extends AbstractElement{
    private By locator = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(4)");

    public ElementConfigurationButton(){
        setWebElement(locator);
    }
}
