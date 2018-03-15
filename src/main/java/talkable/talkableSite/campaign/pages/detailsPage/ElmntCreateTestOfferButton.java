package talkable.talkableSite.campaign.pages.detailsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateTestOfferButton extends AbstractElement{
    private static final By locator = By.linkText("Create test offer");

    ElmntCreateTestOfferButton(){
        setWebElement(locator);
    }

}
