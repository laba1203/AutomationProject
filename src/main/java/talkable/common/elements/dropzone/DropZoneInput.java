package talkable.common.elements.dropzone;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class DropZoneInput extends AbstractElement{

    private final static By locator = By.cssSelector("body > input:nth-last-child(1)");

    public DropZoneInput(){
        setWebElement(locator);
    }

    public void upload(String pathToFile){
        this.sendKeys(pathToFile);
    }

}
