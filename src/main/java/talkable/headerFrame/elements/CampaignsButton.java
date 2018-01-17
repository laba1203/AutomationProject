package talkable.headerFrame.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class CampaignsButton extends AbstractElement {
    private static  final By campaignsButtonLocator = By.linkText("Campaigns");

    public CampaignsButton(){
        setWebElement(campaignsButtonLocator);
    }
}
