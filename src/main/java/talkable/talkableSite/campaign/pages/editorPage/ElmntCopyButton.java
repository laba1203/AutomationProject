package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCopyButton extends AbstractElement{
    private By locator = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(1)");

    ElmntCopyButton(){
        setWebElement(locator);
    }
}
