package talkable.talkableSite.campaign.pages.editorPage.selectImagePopup;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.common.elements.dropzone.DropZoneInput;
import talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage;
import util.TestArtifactsProvider;

import java.util.ArrayList;

import static talkable.talkableSite.campaign.pages.editorPage.SimpleEditorPage.LocalizationType.IMAGES;

public class PopupSelectImage extends AbstractElementsContainer{
//    private static final By popUpDiv = By.xpath("//div[contains(@class, 'Locale-entries-popup ')]");
    private static final By items = By.xpath("//div[contains(@class, 'list-item ')]");
    private ElmntUploadButton uploadButton = new ElmntUploadButton();
    private ArrayList<ImageTile> imagesList;


    public PopupSelectImage(){
        imagesList = createImageTileList(getElementsList(items));
    }


    public SimpleEditorPage selectImageFromEditor(String imageName){
        selectImage(imageName);
        return new SimpleEditorPage(IMAGES);
    }

    public void selectImage(String imageName){
        findTile(imageName).select();
    }

    private ImageTile findTile(String name){
        for (ImageTile image :
                imagesList) {
            if(image.getName().equals(name)){
                return image;
            }
        }
        Assert.fail("FAILED: Image with name <" + name + "> is not found in the popup");
        return null;
    }

    private ArrayList<ImageTile> createImageTileList(ArrayList<Element> parentElements){
        ArrayList<ImageTile> images = new ArrayList<>();
        for (Element parentElement :
                parentElements) {
            images.add(new ImageTile(parentElement));
        }
        return images;
    }

    public PopupSelectImage uploadImage(String fileName){
        uploadButton.click();
        DropZoneInput dropZoneInput = new DropZoneInput();
        dropZoneInput.sendKeys(TestArtifactsProvider.getImagesFilePath(fileName));
        waitTillFileUploaded();
        return new PopupSelectImage();
    }

    public SimpleEditorPage uploadAndSelect(String fileName){
        PopupSelectImage popup = uploadImage(fileName);
        return popup.selectImageFromEditor(fileName);
    }


    private void waitTillFileUploaded(){
        SuccessUploadedMsg msg = new SuccessUploadedMsg();
        wait.until(ExpectedConditions.visibilityOf(msg.getWebElement()));
        wait.until(ExpectedConditions.invisibilityOf(msg.getWebElement()));
    }


}
