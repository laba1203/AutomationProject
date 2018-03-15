package talkable.talkableSite.campaign.pages.detailsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCopyButton extends AbstractElement{
    private static final By locator = By.linkText("Copy");

    ElmntCopyButton(){
        setWebElement(locator);
    }

}
