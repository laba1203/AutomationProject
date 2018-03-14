package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.WebElement;

class LocalizationConfigRecord extends AbstractElementsContainer{

    Element name;

    LocalizationConfigRecord(WebElement webElement){

    }

    public String getNameText(){
        return name.getText();
    }
}
