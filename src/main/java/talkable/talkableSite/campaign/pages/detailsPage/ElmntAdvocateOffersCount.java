package talkable.talkableSite.campaign.pages.detailsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAdvocateOffersCount extends AbstractElement{
    private static final By locator = By.cssSelector(".Campaign-details-summary-chart-text > div");

    ElmntAdvocateOffersCount(){
        setWebElement(locator);
    }

    static By getStaticLocator(){
        return locator;
    }

}
