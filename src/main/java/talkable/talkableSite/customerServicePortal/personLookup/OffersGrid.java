package talkable.talkableSite.customerServicePortal.personLookup;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

class OffersGrid extends AbstractElementsContainer{

    private static final By rowsLctr = By.xpath("//table[contains(@class, 'CSP-table')]//tbody/tr");

    private ArrayList<OfferRow> getRows(){
        ArrayList<OfferRow> rows = new ArrayList<>();
        for (WebElement wElement:
                driver.findElements(rowsLctr)
                ) {
            rows.add(new OfferRow(wElement));
        }
        return rows;


    }

//    OfferRow findReferralByEmail(String friendEmail){
//        for (OfferRow offer
//                :getRows()
//                ) {
//            if(offer.getEmail().equals(friendEmail)){
//                return offer;
//            }
//        }
//        Assert.fail("FAILED: Referral row with friend email <" + friendEmail + "> was not found in the Person Lookup Info --> Referrals tab");
//        return null;
//    }


    class OfferRow{
        private final By detailsLctr = By.xpath(".//a[contains(@class, 'CSP-row-details-button')]");

        private WebElement parentRowElement;

        OfferRow(WebElement parentElement){
            parentRowElement = parentElement;
        }


        ReferralDetailsSection clickDetails(){
            new Element(parentRowElement.findElement(detailsLctr)).click();
            return new ReferralDetailsSection();
        }

    }

}
