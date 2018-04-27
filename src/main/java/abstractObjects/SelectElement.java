package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SelectElement extends AbstractSelectElement{

    public SelectElement(WebElement webElement){
        setWebElement(webElement);
    }

    public SelectElement(By locator){
        setWebElement(locator);
    }
}
