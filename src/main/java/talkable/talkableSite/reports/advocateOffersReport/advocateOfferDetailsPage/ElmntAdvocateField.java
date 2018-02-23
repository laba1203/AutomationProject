package talkable.talkableSite.reports.advocateOffersReport.advocateOfferDetailsPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntAdvocateField extends AbstractElement{
    private static final By locator = By.cssSelector(".dl-horizontal dd:nth-of-type(2)>a ");

    ElmntAdvocateField(){
        setWebElement(locator);
    }
}
