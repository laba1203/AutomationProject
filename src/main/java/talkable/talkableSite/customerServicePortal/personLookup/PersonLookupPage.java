package talkable.talkableSite.customerServicePortal.personLookup;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.talkableSite.customerServicePortal.AbstractCustomerServicePortalPage;

public class PersonLookupPage extends AbstractCustomerServicePortalPage
{
    private static final By searchFieldInputLctr = By.xpath("//form[@class='base-filter']//input");
    private static final By searchBtnLctr = By.xpath("//form[@class='base-filter']//button");

    private Element searchField = new Element(searchFieldInputLctr, "Search Field");
    private Element searchButton = new Element(searchBtnLctr, "Search button");


    public PersonInfoSection searchPerson(String email){
        searchField.clear();
        searchField.sendKeys(email);
        searchButton.click();
        try {
            return getPersonInfoSection();
        }catch (AssertionError e){
            Assert.fail("FAILED: Person with email <" + email + "> is not found on the CSP --> Person Lookup page. \r\n" + e.getMessage());
            return null;
        }
    }

    public PersonInfoSection getPersonInfoSection(){
        return new PersonInfoSection();
    }



}
