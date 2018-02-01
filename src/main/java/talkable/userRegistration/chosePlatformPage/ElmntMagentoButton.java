package talkable.userRegistration.chosePlatformPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntMagentoButton extends AbstractElement{

    private static final By locator = By.cssSelector("[data-platform='magento']");

    ElmntMagentoButton(){
        setWebElement(locator);
    }
}
