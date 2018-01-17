package talkable.addYourSitePage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class WebAddressInput extends AbstractElement {
    private By webAddressLocator = By.id("site_url");

    public WebAddressInput(){
        setWebElement(webAddressLocator);
    }
}
