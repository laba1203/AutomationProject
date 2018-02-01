package talkable.userRegistration.chosePlatformPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntShopifyButton extends AbstractElement{

    private static final By locator = By.cssSelector("[data-platform='shopify']");

    ElmntShopifyButton(){
        setWebElement(locator);
    }
}
