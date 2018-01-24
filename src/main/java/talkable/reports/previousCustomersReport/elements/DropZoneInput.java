package talkable.reports.previousCustomersReport.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import util.logging.Log;

public class DropZoneInput extends AbstractElement{

    private final By locator = By.cssSelector("body>input");//("input[name='previous_customer_upload[object_key]']");

    public DropZoneInput(){
        setWebElement(locator);
    }



//    @Override
//    public void sendKeys(String value){
//        setAttributes("arguments[0].type = 'visible';");
//        this.getWebElement().sendKeys(value);
//        setAttributes("arguments[0].type = 'hidden';");
//        Log.fileUploadedMsg(value, this);
//    }

//    private void setAttributes(String js){
//        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
//        jse.executeScript(js, this.getWebElement());
//    }
}
