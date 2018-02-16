package abstractObjects;

import org.openqa.selenium.WebElement;

public interface DrivenElement {

    void click();

    boolean isEnabled();

    WebElement getWebElement();

    String getText();

//    DrivenElement createNewInstance() throws IllegalAccessException, InstantiationException;

}
