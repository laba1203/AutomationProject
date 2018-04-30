package talkable.talkableSite.siteSettings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntIntegrationSettingsButton extends AbstractElement{
    private static final By locator = By.xpath("//a[contains(@class, 'ac-site-navigation-integration')]");

    ElmntIntegrationSettingsButton(){
        setWebElement(locator);
    }
}
