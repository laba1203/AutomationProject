package talkable.talkableSite.reports.purchasesReport.createNewPurchasePage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCouponCodeInput extends AbstractElement{

    private static final By locator = By.cssSelector("input[name='origin[coupon_code]']");

    ElmntCouponCodeInput(){
        setWebElement(locator);
    }

}
