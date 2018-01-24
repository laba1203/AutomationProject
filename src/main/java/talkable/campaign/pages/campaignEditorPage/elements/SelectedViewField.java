package talkable.campaign.pages.campaignEditorPage.elements;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class SelectedViewField extends AbstractElement{
    private By locator = By.cssSelector(".editor-view-setup-switcher span");

    public SelectedViewField(){
        setWebElement(locator);
    }


}
