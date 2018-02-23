package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.viewRecordsContainer.recordViewContainer;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ViewName extends AbstractElement{

    private By locator;

    ViewName(By locator){
        this.locator = locator;
        setWebElement(locator);
    }


}
