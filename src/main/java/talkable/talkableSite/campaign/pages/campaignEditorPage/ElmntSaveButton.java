package talkable.talkableSite.campaign.pages.campaignEditorPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSaveButton extends AbstractElement{
    private static final By locator = By.cssSelector("[data-action = 'Save changes']");

    ElmntSaveButton(){
        setWebElement(locator);
    }
}
