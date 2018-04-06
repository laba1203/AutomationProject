package talkable.talkableSite.headerFrame.elements.menuFrame;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntLogoutButton extends AbstractElement {

    private static final By locator = By.linkText("Logout");

    ElmntLogoutButton(){
        setWebElement(locator);
    }
}
