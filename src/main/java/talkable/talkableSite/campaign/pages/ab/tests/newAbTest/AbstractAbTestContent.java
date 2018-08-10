package talkable.talkableSite.campaign.pages.ab.tests.newAbTest;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.WebElement;

class AbstractAbTestContent extends AbstractElementsContainer {

    private WebElement ownElement;

    void setParentElement(WebElement webElement){
        ownElement = webElement;
    }

    WebElement getParentElement() {
        return ownElement;
    }
}
