package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSaveButton extends AbstractElement{
    private static final By locator = By.cssSelector("[data-action = 'Save changes']");

    ElmntSaveButton(){
        setWebElement(locator);
    }
}
