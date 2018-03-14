package talkable.talkableSite.campaign.pages.campaignEditorPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntConfigurationButton extends AbstractElement{
    private static  final By locator = By.cssSelector(".Locale-entries-localizations-filters div:nth-of-type(4)");

    ElmntConfigurationButton(){
        setWebElement(locator);
    }
}
