package talkable.talkableSite.headerFrame.elements.siteSelectContainer;


import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class FirstResult extends AbstractElement {

    private By locator = By.cssSelector("ul[class='chosen-results'] li:nth-child(2)");

    FirstResult(){
        setWebElement(locator);

    }
}
