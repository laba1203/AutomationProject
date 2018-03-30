package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntWebAddressInput extends AbstractElement {
    private static final By webAddressLocator = By.id("site_url");

    ElmntWebAddressInput(){
        setWebElement(webAddressLocator);
    }
}
