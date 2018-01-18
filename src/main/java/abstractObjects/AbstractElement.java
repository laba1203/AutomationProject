package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.DriverConfig;
import util.logging.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElement implements DrivenElement{

    private WebElement webElement;
    private By locator;
    private Log log = new Log();
    private WebDriver driver = new DriverConfig().getDriver();


    public void setWebElement(By locator)
    {
        webElement = driver.findElement(locator);
        this.locator = locator;

    }

    public void setWebElement(WebElement webElement){
        this.webElement = webElement;
    }


    public void click()
    {
        this.webElement.click();
        log.clickMsg(this);
    }

    public void sendKeys(String value)
    {
        this.webElement.sendKeys(value);
        log.enterValueMsg(value, this);
    }

    private boolean isElementPresent()
    {
        return driver.findElements(locator).size() > 0;
    }

    public void enterTextAndClickENTER(String value){
        this.webElement.sendKeys(value + Keys.RETURN);
        log.enterValueAndClickEnterMsg(value, this);
    }

    public String getText(){
        return this.webElement.getText();
    }

    public boolean isEnabled(){
        if(!webElement.isEnabled()){
            log.webElementIsNotActiveMsg(this);
        }

        return webElement.isEnabled();
    }

//    public Object createNewInstance(DrivenElement element) throws IllegalAccessException, InstantiationException {
//        return element.getClass().newInstance();
//    }

    /**To update below part
    *     Start:     ********/
//    protected WebElement getActiveElement(List<WebElement> elements){
//        ArrayList<WebElement> activeElements = (ArrayList<WebElement>) getListOfActiveElements(elements);
//        verifyCountOfElements(activeElements, 1);
//        return activeElements.get(0);
//
//    }
//
//    private void verifyCountOfElements(List<WebElement> elements, int expectedCount){
//        Assert.assertEquals(elements.size(), expectedCount, log.incorrectCountOFWebElementsMsg());
//    }
//
//    private List<WebElement> getListOfActiveElements(List<WebElement> allElements){
//        ArrayList<WebElement> activeElements = new ArrayList<WebElement>();
//
//        for (WebElement element: allElements) {
//            if(element.isEnabled()){
//                activeElements.add(element);
//            }
//        }
//        return activeElements;
//    }
//
//    //**** End ******//

    public WebElement getWebElement() {
        return webElement;
    }

    public By getLocator(){
        return this.locator;
    }


}

