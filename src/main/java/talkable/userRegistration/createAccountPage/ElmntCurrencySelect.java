package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractSelectElement;

class ElmntCurrencySelect extends AbstractSelectElement{

    private static final String xpath = ".//*[@name='account[sites_attributes][0][currency_code]']";

    ElmntCurrencySelect(){
        setWebElement(xpath);
    }
}
