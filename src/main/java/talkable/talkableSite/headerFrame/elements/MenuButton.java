package talkable.talkableSite.headerFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class MenuButton extends AbstractElement {
    private static  final By menuButtonLocator = By.className("ac-user-menu");

    public MenuButton(){
        setWebElement(menuButtonLocator);
    }
}
