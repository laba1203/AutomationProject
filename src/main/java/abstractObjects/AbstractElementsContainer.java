package abstractObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.DriverConfig;
import util.Screenshot;
import util.logging.Log;

public abstract class AbstractElementsContainer
{
    protected WebDriver driver = new DriverConfig().getDriver();
    private Screenshot screenshot;
    protected WebDriverWait wait = new DriverConfig().getExplicitWait();


    public void isPageOpened(String title){
        if(!verifyPageTitle(title)) {
            screenshot.getScreenshot();
        }
        Assert.assertEquals(title, driver.getTitle(), Log.pageNotOpenedMsg(this));
    }

//    public void initiateVisibleElements(DrivenElement[] containers) {
//        for (DrivenElement element: containers) {
//            try {
//                DrivenElement obj = element.createNewInstance();
////                element = obj;
//            } catch (IllegalAccessException | InstantiationException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

    private boolean verifyPageTitle(String title){
        return driver.getTitle().equals(title);
    }

    public void refresh(){
        driver.navigate().refresh();
    }

    public boolean isElementPresent(AbstractElement element){
        return driver.findElements(element.getLocator()).size() > 0;
    }



}
