package talkable.talkableSite.headerFrame.elements.siteSelectContainer;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class SiteRecord extends AbstractElement{

//    private By locator = By.linkText();
    private FirstResult firstSite = new FirstResult();

    SiteRecord(String linkText){
        setWebElement(getDriver().findElement(By.linkText(linkText)));
    }

}
