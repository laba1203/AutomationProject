package talkable.IntegrationInstructionPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntCreateCampaignButton extends AbstractElement{

    private By locator = By.linkText("Create campaigns");

    ElmntCreateCampaignButton(){
        setWebElement(locator);
    }
}
