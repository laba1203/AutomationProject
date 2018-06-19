package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import util.logging.Log;

import java.util.List;

public abstract class AbstractSelectElement extends AbstractElement{

    private static final String buttonXpath = "./../div/a";
    private static final String selectedItemXpath = buttonXpath + "/span";
    private static final String searchFieldXpath = "./../div/div[@class='chosen-drop']//input";
    private static final String allItemsXpath = "./following::div//ul/child::li[contains(@class, 'active-result')]";
    private static final String firstItemXpath = allItemsXpath + "[1]";

    private WebElement selectWebElement;
    private Element button;

    @Override
    public void setWebElement(WebElement selectWebElement){
        this.selectWebElement = selectWebElement;
        button = new Element(findChildWebElement(this.selectWebElement, buttonXpath));
    }

    @Override
    public void setWebElement(By locator){
        setWebElement(getDriver().findElement(locator));
    }

    public void selectByVisibleText(String text){

        new Actions(getDriver()).moveToElement(button.getWebElement()/*, 0, 200*/);

        button.click();
        clickByText(text);
        //Verify that text is selected
        verifySelectedItem(text);
        Log.itemSelectedFromDropdownMsg(text);
    }

    public void searchByText(String text){
        button.click();
        populateSearchField(text);
    }

    private void populateSearchField(String text){
//        new Element(selectWebElement.findElement(By.xpath(searchFieldXpath))).sendKeys(text);
        new Element(findChildWebElement(selectWebElement, searchFieldXpath)).sendKeys(text);
    }

    private void verifySelectedItem(String expectedItemName){
//        Element selectedItem = new Element(selectWebElement.findElement(By.xpath(selectedItemXpath)));
        Element selectedItem = new Element(findChildWebElement(selectWebElement, selectedItemXpath));
        Assert.assertEquals(selectedItem.getText(), expectedItemName);
    }

    public void searchAndSelect(String text){
        button.click();
        populateSearchField(text);
        //click to first element in the list:
//        new Element(selectWebElement.findElement(By.xpath(firstItemXpath))).click();
        new Element(findChildWebElement(selectWebElement, firstItemXpath)).click();
        verifySelectedItem(text);
    }

    public String getSelectedItemText(){
//        Element selectedItem = new Element(selectWebElement.findElement(By.xpath(selectedItemXpath)));
        Element selectedItem = new Element(findChildWebElement(selectWebElement, selectedItemXpath));
        return selectedItem.getText();
    }

    private void clickByText(String text){
        List<WebElement> items = selectWebElement.findElements(By.xpath(allItemsXpath));
        boolean isFound = false;
        for (WebElement li : items) {
            if(li.getText().equals(text)){
                //li.click();
                new Element(li).click();
                isFound = true;
                break;
            }
        }
        if(!isFound){
            Assert.fail("FAILED: Element with text <" + text + "> is not found in drop down list");
        }
    }

    private WebElement findChildWebElement(WebElement parent, String childXpath){
        try {
            return parent.findElement(By.xpath(childXpath));
        }
        catch (NoSuchElementException e){
            Assert.fail("FAILED: Not able to find element.\r\n" + e.getLocalizedMessage());
            return null;
        }
    }


    @Override
    public WebElement getWebElement(){
        return button.getWebElement();
    }

}