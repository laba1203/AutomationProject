package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.viewRecordsContainer.recordViewContainer;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ViewVisible extends AbstractElement{

    private By locator;

    ViewVisible(By locator){
        this.locator = locator;
        setWebElement(locator);
    }
}
