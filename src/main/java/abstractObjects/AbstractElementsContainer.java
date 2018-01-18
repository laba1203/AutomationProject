package abstractObjects;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import util.DriverConfig;
import util.Screenshot;
import util.logging.Log;

public abstract class AbstractElementsContainer
{
    public WebDriver driver = new DriverConfig().getDriver();
    private Log log = new Log();
    Screenshot screenshot;


    public void isPageOpened(String title){
        if(!verifyPageTitle(title)) {
            screenshot.getScreenshot();
        }
        Assert.assertEquals(title, driver.getTitle(), log.pageNotOpenedMsg(this));
    }

//    public void initiateVisibleElements(DrivenElement[] elements) {
//        for (DrivenElement element: elements) {
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



}
