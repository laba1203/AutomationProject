package abstractObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.DriverConfig;
import util.Screenshot;
import util.WaitFactory;
import util.logging.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElementsContainer
{
    protected WebDriver driver = DriverConfig.getDriver();
    private Screenshot screenshot;
    private WaitFactory waitFactory = new WaitFactory();
    protected WebDriverWait wait = waitFactory.getExplicitWait();

    private String windowHandle;


    public void isPageOpened(String title){
        if(!verifyPageTitle(title)) {
            screenshot.makeScreenshot();
        }
        Assert.assertEquals(title, driver.getTitle(), Log.pageNotOpenedMsg(this));
    }

    protected WaitFactory waitFactory(){
        return waitFactory;
    }


    private boolean verifyPageTitle(String title){
        return driver.getTitle().equals(title);
    }

    public Object refresh(){
        driver.navigate().refresh();
        try {
            return this.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            Assert.fail("FAILED: " + e.getMessage());
            return null;
        }
    }

    protected boolean isElementPresent(By locator){
        System.out.println("DEBAG: Elements count: " + driver.findElements(locator).size());
        return driver.findElements(locator).size() > 0;
    }

    public String getWindowHandle(){
        windowHandle = driver.getWindowHandle();
        return windowHandle;
    }

    protected void selectByText(String text, ArrayList<Element> items){
        DrivenElement element = getElementByText(text, items);
        if(element == null){
            Log.testFailed("Elements of the list:");
            for (Element item :
                    items) {
                System.out.println("  " + item.getText());
            }
            Assert.fail("FAILED: Element with text <" + text + "> was not found in the above list" );
        }
        element.click();
    }

    protected DrivenElement getElementByText(String text, ArrayList<Element> items){
        for (DrivenElement li : items) {
            if(li.getText().equals(text)){
                return  li;
            }
        }
        Log.logRecord("Element with text <" + text + "> is not found");
        return null;
    }

    protected ArrayList<Element> getElementsList(By by){
        List<WebElement> elements = driver.findElements(by);
        return convert(elements);
    }

    protected ArrayList<Element> getElementsList(WebElement parentWebElement, String xpath){
        List<WebElement> elements = parentWebElement.findElements(By.xpath(xpath));
        return convert(elements);
    }

    private ArrayList<Element> convert(List<WebElement> from){
        ArrayList<Element> outList = new ArrayList<>();
        for (WebElement element :
                from) {
            outList.add(new Element(element));
        }
        return outList;
    }







}
