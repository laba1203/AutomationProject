package abstractObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import util.DriverConfig;
import util.logging.Log;


public abstract class AbstractElement implements DrivenElement{

    private WebElement webElement;
    private By locator;
    private WebDriver driver = DriverConfig.getDriver();
    private Actions actions = new Actions(driver);
    private String elementName = this.getClass().getName();


    protected void setWebElement(By locator)
    {
        try {
            webElement = driver.findElement(locator);
            this.locator = locator;
        }
        catch (NoSuchElementException e){
            Assert.fail( "Element <" + elementName + "> was not found on the page. \r\n" + e.getMessage());
        }
    }

    public void setWebElement(WebElement webElement){
        try {
            this.webElement = webElement;
        }catch (NoSuchElementException e){
            Assert.fail(e.getMessage());
        }
    }

    public void moveToElementAndClick(){
        initializeWebElement();
        actions.moveToElement(this.webElement).perform();
        click();
    }

    public void click()
    {
        initializeWebElement();
//        actions.moveToElement(this.webElement).perform();
        this.webElement.click();

        logClick();
    }

    protected void logClick(){
        Log.clickMsg(elementName);
    }
    protected void logSendKeys(String value){
        Log.enterValueMsg(value, elementName);
    }

    private void initializeWebElement(){
        if(locator != null){
            setWebElement(locator);
        }
    }

    public void sendKeys(String value)
    {
        initializeWebElement();
        this.webElement.sendKeys(value);
        logSendKeys(value);
    }

    public void enterTextAndClickENTER(String value){
        this.webElement.sendKeys(value + Keys.RETURN);
        Log.enterValueAndClickEnterMsg(value, this);
    }

    public String getText(){
        initializeWebElement();
        try {
            return this.webElement.getText();
        }catch (StaleElementReferenceException e){
            Log.debagRecord("Catch  StaleElementReferenceException during getText() for the element <" + this.getClass().getName() + ">. Trying to get Text once again...");
            initializeWebElement();
            return this.webElement.getText();
        }
    }

    public boolean isEnabled(){
        if(!webElement.isEnabled()){
            Log.webElementIsNotActiveMsg(this);
        }

        return webElement.isEnabled();
    }

    public WebElement getWebElement() {
        initializeWebElement();
        return webElement;
    }

    public By getLocator(){
        return this.locator;
    }

    protected WebDriver getDriver(){
        return this.driver;
    }

    public void moveMouseOver(){
        initializeWebElement();
        Actions builder = new Actions(driver);
        builder.moveToElement(webElement).build().perform();
        Log.moveMouseOverMsg(this);
    }

    public void clear(){
        initializeWebElement();
        webElement.clear();
        Log.elementClearedMsg(this);
    }

    public void clearAndSendKeys(String text){
        clear();
        sendKeys(text);
    }

    public String getAttribute(String attributeName){
        initializeWebElement();
        return this.webElement.getAttribute(attributeName);
    }

    void setElementNameForLog(String name){
        this.elementName = name;
    }

}

