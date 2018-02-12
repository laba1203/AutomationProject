package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.DriverConfig;
import util.logging.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElement implements DrivenElement{

    private WebElement webElement;
    private By locator;
    private WebDriver driver = new DriverConfig().getDriver();
    protected WebDriverWait wait = new DriverConfig().getExplicitWait();
    private Actions actions = new Actions(driver);


    protected void setWebElement(By locator)
    {
        webElement = driver.findElement(locator);
        this.locator = locator;
    }

    public void setWebElement(WebElement webElement){
        this.webElement = webElement;
    }

    public void click()
    {
        //Newly added part for CampaignRulesPage. To be tested
        actions.moveToElement(this.webElement).perform();
        //end

        this.webElement.click();
        Log.clickMsg(this);
    }

    public void sendKeys(String value)
    {
        this.webElement.sendKeys(value);
        Log.enterValueMsg(value, this);
    }

    private boolean isElementPresent()
    {
        return driver.findElements(locator).size() > 0;
    }

    public void enterTextAndClickENTER(String value){
        this.webElement.sendKeys(value + Keys.RETURN);
        Log.enterValueAndClickEnterMsg(value, this);
    }

    public String getText(){
        return this.webElement.getText();
    }

    public boolean isEnabled(){
        if(!webElement.isEnabled()){
            Log.webElementIsNotActiveMsg(this);
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

    public void waitTillElementPopulatedByText(String text){
        wait.until(ExpectedConditions.textToBePresentInElement(this.webElement, text));
    }

    public void waitElementWithTextDisappeared(String text){
        wait.until(ExpectedConditions.invisibilityOfElementWithText(this.locator, text));
    }

    public void moveMouseOver(){
        Actions builder = new Actions(driver);
        builder.moveToElement(webElement).build().perform();
        Log.moveMouseOverMsg(this);
    }

    public void clear(){
        webElement.clear();
        Log.elementClearedMsg(this);
    }




}

