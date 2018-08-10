package talkable.talkableSite.campaign.pages.ab.tests.newAbTest;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.talkableSite.campaign.pages.editorPage.selectImagePopup.PopupSelectImage;
import talkable.talkableSite.campaign.pages.multiCampaignEditor.ContentValueRecord;
import util.Util;

class ContentImageInput extends AbstractAbTestContent implements ContentValueRecord{
    private static final By selectImageBtnLctr = By.xpath(".//*[@class='test-variants-row-img-box']");
    private static final By imgLctr = By.xpath("//img");


    public ContentImageInput(WebElement element){
        setParentElement(element);
    }

    @Override
    public void update(String newFileName) {
        new Element(getParentElement(), selectImageBtnLctr, "Select Image button").click();
        PopupSelectImage popup = new PopupSelectImage();
        popup.selectImage(newFileName);
    }

    @Override
    public String getText(){
        return Util.getLastUrlResource(
                new Element(getParentElement(), imgLctr, "Image element")
                        .getAttribute("src")
        );
    }




}
