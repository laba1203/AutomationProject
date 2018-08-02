package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import util.TestArtifactsProvider;
import util.TestDataGenerator;
import util.logging.Log;

class CreateNewFontPopup extends AbstractElementsContainer{

    private static final By fontNameLctr = By.xpath("//*[@id='font-uploader-name']");
    private static final By woffInputLctr = By.xpath("//input[@name='FontWoff']");
    private static final By woff2InputLctr = By.xpath("//input[@name='FontWoff2']");
    private static final By createFontBtnLctr = By.xpath("//button[@type='submit']");

    private Element fontName = new Element(fontNameLctr, "Font name field");
    private Element woffInput = new Element(woffInputLctr, "woff file input");
    private Element woff2Input = new Element(woff2InputLctr, "woff2 file input");
    private Element createNewFont = new Element(createFontBtnLctr, "Create font button");

    CreateNewFontPopup populateFields(String fontName, String woffFile, String woff2File){
        String woffPath = TestArtifactsProvider.getFontsFilePath(woffFile);
        String woff2Path = TestArtifactsProvider.getFontsFilePath(woff2File);

        this.fontName.sendKeys(fontName);
        woffInput.sendKeys(woffPath);
        woff2Input.sendKeys(woff2Path);

        return new CreateNewFontPopup();
    }

    NewFontUploadedPopup createFont(){
        createNewFont.click();
        NewFontUploadedPopup page = new NewFontUploadedPopup();
        Log.logRecord("New font was uploaded.");
        return page;
    }

}
