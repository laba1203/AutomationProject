package talkable.talkableSite.campaign.pages.campaignEditorPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCopyButton extends AbstractElement{
    private By locator = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(1)");

    ElmntCopyButton(){
        setWebElement(locator);
    }
}
