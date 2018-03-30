package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCurrencySelect extends AbstractElement {
    private static final By currencySelectLocator = By.id("site_currency_code");

    ElmntCurrencySelect(){
        setWebElement(currencySelectLocator);
    }


}
