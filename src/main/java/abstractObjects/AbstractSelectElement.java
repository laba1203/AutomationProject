package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.logging.Log;

import java.util.List;

public abstract class AbstractSelectElement extends AbstractElement{

    private WebDriver driver = getDriver();

    private String searchFieldLocator;
    private String selectedItemLocator;
    private String allItemsLocator;
    private String buttonLocator;

    private WebElement button;
    private WebElement selectedItem;


    protected void setWebElement(String xpathOfSelectElement){
        setLocatorsFromSelectXpath(xpathOfSelectElement);
        button = driver.findElement(By.xpath(buttonLocator));
    }

    private void setLocatorsFromSelectXpath(String xpathLocator){
        buttonLocator = xpathLocator + "/../div/a";
        selectedItemLocator = buttonLocator + "/span";
        searchFieldLocator = xpathLocator + " /../div/div[@class='chosen-drop']//input";
        allItemsLocator = xpathLocator + "/..//ul/child::li";
    }


    public void selectByVisibleText(String text){
        button.click();
//        driver.findElement(By.xpath(searchFieldLocator));
        clickByText(text);
        //Verify that text is selected
        selectedItem = driver.findElement(By.xpath(selectedItemLocator));
        Assert.assertEquals(selectedItem.getText(), text);
    }

    public void searchByText(String text){
        button.click();
        WebElement searchField = driver.findElement(By.xpath(searchFieldLocator));
        searchField.sendKeys(text);
    }

    public void searchAndSelect(String text){
        button.click();
        WebElement searchField = driver.findElement(By.xpath(searchFieldLocator));
        searchField.sendKeys(text);
        WebElement firstItem = driver.findElement(By.xpath(allItemsLocator + "[1]"));
        firstItem.click();
        //Verify that text is selected
        selectedItem = driver.findElement(By.xpath(selectedItemLocator));
        Assert.assertEquals(selectedItem.getText(), text);
    }

    public String getSelectedItemText(){
        selectedItem = driver.findElement(By.xpath(selectedItemLocator));
        return selectedItem.getText();
    }

    private void clickByText(String text){
        List<WebElement> items= driver.findElements(By.xpath(allItemsLocator));
        for (WebElement li : items) {
            if(li.getText().equals(text)){
                li.click();
            }
        }
    }


    public void clickButton() {
        button.click();
    }

}
