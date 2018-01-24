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

    public ArrayList<WebElement> getWebElements(By locator){
        return (ArrayList<WebElement>) driver.findElements(locator);
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public By getLocator(){
        return this.locator;
    }

    protected WebDriver getDriver(){
        return this.driver;
    }


}

