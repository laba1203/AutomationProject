package talkable.userRegistration.createAccountPage;

import abstractObjects.AbstractSelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class ElmntCurrencySelect extends AbstractSelectElement{

    ElmntCurrencySelect(){
        WebElement webElement = getDriver().findElement(By.xpath("//*[@name='account[sites_attributes][0][currency_code]']"));
        setWebElement(webElement);
    }
}
