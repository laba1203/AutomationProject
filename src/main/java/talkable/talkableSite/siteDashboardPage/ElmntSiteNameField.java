package talkable.talkableSite.siteDashboardPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSiteNameField extends AbstractElement{

    private static final By locator = By.cssSelector(".Campaign-management-header-title");

    ElmntSiteNameField(){
        setWebElement(locator);
    }
}
