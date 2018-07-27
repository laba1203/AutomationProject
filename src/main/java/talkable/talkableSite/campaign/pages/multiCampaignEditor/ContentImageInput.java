package talkable.talkableSite.campaign.pages.multiCampaignEditor;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.campaign.pages.editorPage.selectImagePopup.PopupSelectImage;
import util.Util;

class ContentImageInput extends AbstractElementsContainer implements ContentValueRecord{
    private static final By selectImageBtnLctr = By.xpath("//*[contains(text(), 'New content value')]/..//img");

    private Element selectImage = new Element(selectImageBtnLctr, "Select Image button");

    @Override
    public void update(String newFileName) {
        selectImage.click();
        PopupSelectImage popup = new PopupSelectImage();
        popup.selectImage(newFileName);
    }

    @Override
    public String getText(){
        return Util.getLastUrlResource(selectImage.getAttribute("src"));
    }




}
