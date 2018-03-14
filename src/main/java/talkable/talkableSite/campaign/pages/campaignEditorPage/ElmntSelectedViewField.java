package talkable.talkableSite.campaign.pages.campaignEditorPage;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntSelectedViewField extends AbstractElement{
    private static  final By locator = By.cssSelector(".editor-view-setup-switcher span");

    ElmntSelectedViewField(){
        setWebElement(locator);
    }


}
