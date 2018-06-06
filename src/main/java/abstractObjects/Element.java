package abstractObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Element extends AbstractElement{

//    private String elementNameForLog = "Default Element Name";

    public Element(By by){
        setWebElement(by);
    }

    public Element(By by, String elementName){
        setWebElement(by);
        setElementNameForLog(elementName);
//        elementNameForLog = elementName;
    }

    public Element(WebElement webElement){
        setWebElement(webElement);
    }

    //to create new element which is child of @parentElement
    public Element(Element parent, By by){
        setWebElement(parent.getWebElement().findElement(by));
    }

//    @Override
//    protected void logClick(){
//        Log.clickMsg(elementNameForLog);
//    }

//    @Override
//    protected void logSendKeys(String value){
//        Log.enterValueMsg(value, elementNameForLog);
//    }
}
