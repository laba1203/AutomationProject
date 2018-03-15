package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public abstract class AbstractSelectElement extends AbstractElement{

    private static final String buttonXpath = "./../div/a";
    private static final String selectedItemXpath = buttonXpath + "/span";
    private static final String searchFieldXpath = "./../div/div[@class='chosen-drop']//input";
    private static final String allItemsXpath = "./..//ul/child::li[contains(@class, 'active-result')]";
    private static final String firstItemXpath = allItemsXpath + "[1]";

    private WebElement selectWebElement;
    private Element button;

    @Override
    public void setWebElement(WebElement selectWebElement){
        this.selectWebElement = selectWebElement;
        button = new Element(selectWebElement.findElement(By.xpath(buttonXpath)));
    }

    @Override
    public void setWebElement(By locator){
        setWebElement(getDriver().findElement(locator));
    }

    public void selectByVisibleText(String text){
        button.click();
        clickByText(text);
        //Verify that text is selected
        verifySelectedItem(text);
    }

    public void searchByText(String text){
        button.click();
        populateSearchField(text);
    }


    private void populateSearchField(String text){
        new Element(selectWebElement.findElement(By.xpath(searchFieldXpath))).sendKeys(text);
    }


    private void verifySelectedItem(String expectedItemName){
        Element selectedItem = new Element(selectWebElement.findElement(By.xpath(selectedItemXpath)));
        Assert.assertEquals(selectedItem.getText(), expectedItemName);
    }


    public void searchAndSelect(String text){
        button.click();
        populateSearchField(text);
        //click to first element in the list:
        new Element(selectWebElement.findElement(By.xpath(firstItemXpath))).click();
        verifySelectedItem(text);

    }


    public String getSelectedItemText(){
        Element selectedItem = new Element(selectWebElement.findElement(By.xpath(selectedItemXpath)));
        return selectedItem.getText();
    }


    private void clickByText(String text){
        List<WebElement> items= selectWebElement.findElements(By.xpath(allItemsXpath));
        for (WebElement li : items) {
            if(li.getText().equals(text)){
                li.click();
            }
        }
    }

}