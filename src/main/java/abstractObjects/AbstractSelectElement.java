package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public abstract class AbstractSelectElement extends AbstractElement{

//    private WebDriver driver = getDriver();

//    private String searchFieldLocator;
//    private String selectedItemLocator;
//    private String allItemsLocator;
//    private String buttonLocator;

    private static final String buttonXpath = "./../div/a";
    private static final String selectedItemXpath = buttonXpath + "/span";
    private static final String searchFieldXpath = "./../div/div[@class='chosen-drop']//input";
    private static final String allItemsXpath = "./..//ul/child::li[contains(@class, 'active-result')]";
    private static final String firstItemXpath = allItemsXpath + "[1]";
    private WebElement selectWebElement;

//    private WebElement button;
    private Element button;
//    private WebElement selectedItem;
    private Element selectedItem;




    protected void setWebElement(String xpathOfSelectElement){
        setLocatorsFromSelectXpath(xpathOfSelectElement);
//        button = driver.findElement(By.xpath(buttonLocator));
    }



    ///Not completed part
    @Override
    public void setWebElement(WebElement selectWebElement){
        this.selectWebElement = selectWebElement;
        button = new Element(selectWebElement.findElement(By.xpath(buttonXpath)));


    }

    private void setElementsFromWebElement(WebElement webElement){

    }
    /// end ///

    private void setLocatorsFromSelectXpath(String xpathLocator){
//        buttonLocator = xpathLocator + "/../div/a";
//        selectedItemLocator = buttonLocator + "/span";
//        searchFieldLocator = xpathLocator + " /../div/div[@class='chosen-drop']//input";
//        allItemsLocator = xpathLocator + "/..//ul/child::li";
    }


//    public void selectByVisibleText(String text){
//        button.click();
//        clickByText(text);
//        //Verify that text is selected
//        selectedItem = driver.findElement(By.xpath(selectedItemLocator));
//        Assert.assertEquals(selectedItem.getText(), text);
//    }

//version2:
    public void selectByVisibleText(String text){
        button.click();
        clickByText(text);
        //Verify that text is selected
        verifySelectedItem(text);
    }




//    public void searchByText(String text){
//        button.click();
//        WebElement searchField = driver.findElement(By.xpath(searchFieldLocator));
//        searchField.sendKeys(text);
//    }

//    version2:
    public void searchByText(String text){
        button.click();
        populateSearchField(text);
    }


    private void populateSearchField(String text){
        new Element(selectWebElement.findElement(By.xpath(searchFieldXpath))).sendKeys(text);
    }

    private void verifySelectedItem(String expectedItemName){
        selectedItem = new Element(selectWebElement.findElement(By.xpath(selectedItemXpath)));
        Assert.assertEquals(selectedItem.getText(), expectedItemName);
    }





//    public void searchAndSelect(String text){
//        button.click();
//        WebElement searchField = driver.findElement(By.xpath(searchFieldLocator));
//        searchField.sendKeys(text);
//        WebElement firstItem = driver.findElement(By.xpath(allItemsLocator + "[1]"));
//        firstItem.click();
//        //Verify that text is selected
//        selectedItem = driver.findElement(By.xpath(selectedItemLocator));
//        Assert.assertEquals(selectedItem.getText(), text);
//    }

//    version2:
    public void searchAndSelect(String text){
        button.click();
        populateSearchField(text);
        //click to first element in the list:
        new Element(selectWebElement.findElement(By.xpath(firstItemXpath))).click();
        verifySelectedItem(text);

    }





//    public String getSelectedItemText(){
//        selectedItem = driver.findElement(By.xpath(selectedItemLocator));
//        return selectedItem.getText();
//    }

//    version2:
    public String getSelectedItemText(){
        Element selectedItem = new Element(selectWebElement.findElement(By.xpath(selectedItemXpath)));
        return selectedItem.getText();
    }





//    private void clickByText(String text){
//        List<WebElement> items= driver.findElements(By.xpath(allItemsLocator));
//        for (WebElement li : items) {
//            if(li.getText().equals(text)){
//                li.click();
//            }
//        }
//    }

    private void clickByText(String text){
        List<WebElement> items= selectWebElement.findElements(By.xpath(allItemsXpath));
        for (WebElement li : items) {
            if(li.getText().equals(text)){
                li.click();
            }
        }
    }

}
