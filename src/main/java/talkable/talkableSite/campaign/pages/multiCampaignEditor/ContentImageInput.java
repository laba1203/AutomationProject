package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.campaign.pages.editorPage.selectImagePopup.PopupSelectImage;

class ContentImageInput extends AbstractElementsContainer implements ContentValueRecord{

    private Element selectImage;

    ContentImageInput(){
        selectImage = new Element(driver.findElement(By.xpath("//*[contains(text(), 'New content value')]/..//img")));
    }


    @Override
    public void update(String newFileName) {
        selectImage.click();
        PopupSelectImage popup = new PopupSelectImage();
        popup.selectImage(newFileName);
    }

    @Override
    public String getText(){
        return selectImage.getAttribute("src");
    }




}
