package talkable.talkableSite.reports.purchasesReport.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateOriginButton extends AbstractElement{

    private static final By locator = By.cssSelector("input[value='Create Origin']");

    ElmntCreateOriginButton(){
        setWebElement(locator);
    }

}
