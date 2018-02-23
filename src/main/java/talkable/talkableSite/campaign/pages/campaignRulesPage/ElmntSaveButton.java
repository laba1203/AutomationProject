package talkable.talkableSite.campaign.pages.campaignRulesPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSaveButton extends AbstractElement{

    private static final By locator = By.cssSelector(".ac-save-changes");

    ElmntSaveButton(){
        setWebElement(locator);
    }

}
