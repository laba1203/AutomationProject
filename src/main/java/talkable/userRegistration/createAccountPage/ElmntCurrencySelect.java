package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractElement;
import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;

class ElmntCurrencySelect extends AbstractSelectElement{

    private static final By locator = By.cssSelector("[name='account[sites_attributes][0][currency_code]']");

    ElmntCurrencySelect(){
        setWebElement(locator);
    }
}
