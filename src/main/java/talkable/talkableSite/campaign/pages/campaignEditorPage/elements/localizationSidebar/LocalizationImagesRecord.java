package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class LocalizationImagesRecord extends AbstractLocalizationRecord{

    private Element editImage;
    private Element pickDiffImage;

    LocalizationImagesRecord(WebElement webElement){
        super(webElement);
        editImage = new Element(webElement.findElement(By.xpath(".//*[@title = 'Edit image']")));
        pickDiffImage = new Element(webElement.findElement(By.xpath(".//*[@title = 'Pick a different image']")));
    }

    public void editImage(){
        editImage.click();
    }

    public PopupSelectImage pickDifferentImage(){
        pickDiffImage.click();
        return new PopupSelectImage();
    }


}
