package talkable.addYourSitePage;

import abstractObjects.AbstractElementsContainer;
import talkable.addYourSitePage.elements.*;

public class AddSitePage extends AbstractElementsContainer {

    private static  final String title = "Site details | Talkable";

    private SiteName siteName;
    private WebAddressInput webAddressInput;
    private PlatformInput platformInput;
    private CurrencySelect currencySelect;
    private AddSiteButton addSiteButton;



    public AddSitePage(){
        //check if page is opened
        isPageOpened(title);

        //initialize webElements:
        siteName = new SiteName();
        webAddressInput = new WebAddressInput();
        platformInput = new PlatformInput();
        currencySelect = new CurrencySelect();
        addSiteButton = new AddSiteButton();

    }

    public void populateForm(String siteName, String url){
        this.siteName.sendKeys(siteName);
        webAddressInput.sendKeys(url);
        addSiteButton.click();
    }


}
