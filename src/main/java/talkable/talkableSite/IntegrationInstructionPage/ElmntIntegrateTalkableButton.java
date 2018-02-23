package talkable.talkableSite.IntegrationInstructionPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntIntegrateTalkableButton extends AbstractElement{

    private By locator = By.linkText("Integrate Talkable");

    ElmntIntegrateTalkableButton(){
        setWebElement(locator);
    }
}
