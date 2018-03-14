package talkable.talkableSite.campaign.pages.campaignEditorPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntImagesButton extends AbstractElement{
    private static final By locator = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(2)");

    ElmntImagesButton(){
        setWebElement(locator);
    }
}
