package talkable.addYourSitePage;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.IntegrationInstructionPage.IntegrationInstructionPage;

public class AddSitePage extends AbstractElementsContainer {

    private static  final String title = "Site details | Talkable";

    private ElmntSiteName siteName;
    private ElmntWebAddressInput webAddressInput;
    private ElmntPlatformInput platformInput;
    private ElmntCurrencySelect currencySelect;
    private ElmntAddSiteButton addSiteButton;



    public AddSitePage(){
        //check if page is opened
        isPageOpened(title);

        //initialize webElements:
        siteName = new ElmntSiteName();
        webAddressInput = new ElmntWebAddressInput();
        platformInput = new ElmntPlatformInput();
        currencySelect = new ElmntCurrencySelect();
        addSiteButton = new ElmntAddSiteButton();

    }

    public IntegrationInstructionPage submitForm(String siteName, String url){
        this.siteName.sendKeys(siteName);
        webAddressInput.sendKeys(url);
        addSiteButton.click();
        return new IntegrationInstructionPage();
    }


}
