package talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

class LocalizationImagesRecord extends AbstractLocalizationRecord{

    private Element editImage;
    private Element pickDiffImage;
    private Element image;


    LocalizationImagesRecord(WebElement webElement){
        super(webElement);
        editImage = new Element(webElement.findElement(By.xpath(".//*[@title = 'Edit image']")));
        pickDiffImage = new Element(webElement.findElement(By.xpath(".//*[@title = 'Pick a different image']")));
        image = new Element(webElement.findElement(By.xpath(".//img")));
    }

    public void editImage(){
        editImage.click();
    }

    public PopupSelectImage pickDifferentImage(){
        pickDiffImage.click();
        return new PopupSelectImage();
    }


    @Override
    public void update(String newValue) {
        //TODO: implement the method.
        Assert.fail("FAILED: update() method is not yet implemented for Images localization record");
    }

    @Override
    public String getValueText() {
        return image.getAttribute("src");
    }
}
