package talkable.talkableSite.siteDashboardPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class SiteNameField extends AbstractElement{

    private By locator = By.cssSelector(".Campaign-management-header-title");

    public SiteNameField(){
        setWebElement(locator);
    }
}
