package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntColorButton extends AbstractElement{
    private static final By locator = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(3)");

    ElmntColorButton(){
        setWebElement(locator);
    }
}
