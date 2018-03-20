package talkable.talkableSite.campaign.pages.editorPage.selectImagePopup;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ElmntUploadButton extends AbstractElement{
    private static final By locator = By.xpath("//div[contains(@class, 'upload-trigger')]");

    ElmntUploadButton(){
        setWebElement(locator);
    }
}
