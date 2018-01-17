package talkable.addYourSitePage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CurrencySelect extends AbstractElement {
    private By currencySelectLocator = By.id("site_currency_code");

    public CurrencySelect(){
        setWebElement(currencySelectLocator);
    }


}
