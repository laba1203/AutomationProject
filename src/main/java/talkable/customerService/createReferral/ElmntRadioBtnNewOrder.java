package talkable.customerService.createReferral;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ElmntRadioBtnNewOrder extends AbstractElement {
    private By locator = By.xpath("//*[@id=\"new_build_referral\"]/div[1]/div[3]/label");
    public ElmntRadioBtnNewOrder(){setWebElement(locator);
    }

}
