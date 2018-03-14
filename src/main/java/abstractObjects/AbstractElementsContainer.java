package abstractObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.DriverConfig;
import util.Screenshot;
import util.logging.Log;

import java.util.ArrayList;

public abstract class AbstractElementsContainer
{
    protected WebDriver driver = new DriverConfig().getDriver();
    private Screenshot screenshot;
    protected WebDriverWait wait = new DriverConfig().getExplicitWait();
    protected String windowHandle;


    public void isPageOpened(String title){
        if(!verifyPageTitle(title)) {
            screenshot.getScreenshot();
        }
        Assert.assertEquals(title, driver.getTitle(), Log.pageNotOpenedMsg(this));
    }


    private boolean verifyPageTitle(String title){
        return driver.getTitle().equals(title);
    }

    public Object refresh(){
        driver.navigate().refresh();
        try {
            return this.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
            Assert.fail("FAILED: " + e.getMessage());
            return null;
        }
    }

    public boolean isElementPresent(By locator){
        System.out.println("DEBAG: Elements count: " + driver.findElements(locator).size());
        return driver.findElements(locator).size() > 0;
    }

    public String getWindowHandle(){
        windowHandle = driver.getWindowHandle();
        return windowHandle;
    }

    protected void selectByText(String text, ArrayList<DrivenElement> items){
        getElementByText(text, items).click();

//        for (DrivenElement li : items) {
//            if(li.getText().equals(text)){
//                li.click();
//            }
//        }
    }

    protected DrivenElement getElementByText(String text, ArrayList<DrivenElement> items){
        for (DrivenElement li : items) {
            if(li.getText().equals(text)){
                return  li;
            }
        }
        System.out.println("Element with text <" + text + "> is not found");
        return null;
    }


}
