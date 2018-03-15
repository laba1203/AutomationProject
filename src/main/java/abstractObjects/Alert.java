package abstractObjects;

import org.openqa.selenium.WebDriver;
import util.DriverConfig;
import util.logging.Log;

public class Alert extends AbstractElementsContainer{

    protected WebDriver driver = new DriverConfig().getDriver();
    private org.openqa.selenium.Alert alert;

    public Alert(){
        alert = driver.switchTo().alert();
    }

    public void confirm(){
        alert.accept();
        Log.alertAccepted();
    }

}
