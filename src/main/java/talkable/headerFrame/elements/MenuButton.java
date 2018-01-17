package talkable.headerFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class MenuButton extends AbstractElement {
    private static  final By menuButtonLocator = By.className("base-btn-icon-wrapper");

    public MenuButton(){
        setWebElement(menuButtonLocator);
    }
}
