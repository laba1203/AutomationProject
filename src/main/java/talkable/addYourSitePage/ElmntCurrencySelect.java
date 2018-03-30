package talkable.addYourSitePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntCurrencySelect extends AbstractElement {
    private By currencySelectLocator = By.id("site_currency_code");

    public ElmntCurrencySelect(){
        setWebElement(currencySelectLocator);
    }


}
