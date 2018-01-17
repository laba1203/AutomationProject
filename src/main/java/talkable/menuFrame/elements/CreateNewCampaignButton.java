package talkable.menuFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CreateNewCampaignButton extends AbstractElement {

    private static final By createCampaignButtonLocator = By.linkText("Create New Campaign");

    public CreateNewCampaignButton(){
        setWebElement(createCampaignButtonLocator);
    }
}
