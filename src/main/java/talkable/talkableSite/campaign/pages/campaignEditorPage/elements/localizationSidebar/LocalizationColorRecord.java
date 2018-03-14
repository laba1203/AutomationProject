package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.WebElement;

public class LocalizationColorRecord extends AbstractElementsContainer{

    Element name;

    LocalizationColorRecord(WebElement webElement){

    }

    public String getNameText(){
        return name.getText();
    }
}
