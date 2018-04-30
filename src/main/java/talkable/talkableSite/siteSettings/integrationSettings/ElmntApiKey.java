package talkable.talkableSite.siteSettings.integrationSettings;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntApiKey extends AbstractElement{
    private static final By locator = By.xpath("//input[@id = 'site-api-key']");

    ElmntApiKey(){
        setWebElement(locator);
    }
}
