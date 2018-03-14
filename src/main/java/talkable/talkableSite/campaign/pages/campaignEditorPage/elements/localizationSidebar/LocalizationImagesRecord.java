package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.WebElement;

class LocalizationImagesRecord extends AbstractElementsContainer{

    Element name;

    LocalizationImagesRecord(WebElement webElement){

    }

    public String getNameText(){
        return name.getText();
    }
}
