package talkable.talkableSite.campaign.pages.campaignDetailsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAdvocateOffersCount extends AbstractElement{
    private static final By locator = By.cssSelector(".Campaign-details-summary-chart-text > div");

    ElmntAdvocateOffersCount(){
        setWebElement(locator);
    }

}
