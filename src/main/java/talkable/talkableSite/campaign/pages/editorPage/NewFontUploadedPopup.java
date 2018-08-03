package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;

class NewFontUploadedPopup extends AbstractElementsContainer{

    private static final By switchToEditorLctr = By.xpath("//button[contains(text(), 'Switch to editor')]");

    private Element switchToEditorBtn = new Element(switchToEditorLctr, "Switch to editor button");

    SimpleEditorPage switchToEditor(){
        switchToEditorBtn.click();
        return new SimpleEditorPage();
    }
}
