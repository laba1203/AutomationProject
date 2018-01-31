package talkable.IntegrationInstructionPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntDontShowItAgain extends AbstractElement{

    private By locator = By.cssSelector(".mtl>span");

    ElmntDontShowItAgain(){
        setWebElement(locator);
    }
}
