package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.common.elements.alert.Alert;
import util.TestArtifactsProvider;
import util.logging.Log;

public class HtmlEditorPage extends AbstractEditorPage{

    private static final By extraBtnLctr = By.xpath("//*[@data-editor-toggle = 'extra-fields']");
    private static final By viewDestroyedMcsgLctr = By.xpath("//div[contains(text(), 'destroyed')]");
    private static final By cssPanelTitleLctr = By.xpath("//div[@class='editor-panel-tip-title']");
    private static final By emailSubjectInExtraPopup = By.xpath("//textarea[@name='view_setup[caption]']");
    private static final By saveChangesBtnInExtaLctr = By.xpath("//*[contains(@class,'editor-popup')]/div[text() = 'Save changes']");
    private static final By htmlTextAreaLctr = By.xpath("//div[@id='ace-template']/textarea[@class='ace_text-input']");
    private static final By cssTextAreaLctr = By.xpath("//div[@id='ace-css']/textarea[@class='ace_text-input']");
    private static final By firstCssRowLctr = By.xpath("//div[@id='ace-css']//div[contains(@class, 'ace_text-layer')]/div[@class='ace_line_group'][1]");
    private static final By fileUploaderLctr = By.xpath("//*[@data-editor-toggle='file-uploader']");
    private static final By fileUploaderInputLctr = By.xpath("//input[@type='file']");


    private Element extraBtn = new Element(extraBtnLctr, "Extra button");
    private Element htmlTextArea = new Element(htmlTextAreaLctr, "HTML Text field");
    private Element cssTextArea = new Element(cssTextAreaLctr, "CSS Text field");
    private Element fileUploaderBtn = new Element(fileUploaderLctr, "Files button");

    public HtmlEditorPage(){
        //elements initiation to make sure that Html Editor is opened.
        new Element(cssPanelTitleLctr);
    }

    public HtmlEditorPage switchViewByNameOnHtmlEditor(String name){
        switchViewByName(name);
        return new HtmlEditorPage();
    }

    public CreateNewViewPage clickCreateNewView(){
        return openViewList()
                .clickCreateNewView()
                .proceedInWarningPopup();
    }

    public HtmlEditorPage deleteView(String viewName){
        openViewList()
                .findViewRecord(viewName)
                .delete();
        new Alert().confirm();
        waitViewDestroyedMsg();
        return new HtmlEditorPage();
    }

    public void waitViewDestroyedMsg(){
        new Element(viewDestroyedMcsgLctr);
    }

    public HtmlEditorPage updateExtraEmailSubject(String newSubject){
        extraBtn.click();
        new Element(emailSubjectInExtraPopup, "Email Subject in Extra")
                .clearAndSendKeys(newSubject);
        HtmlEditorPage page = saveChangesInExtraPopup();
        Log.logRecord("Email Subject updated in HTML Editor --> Extra popup. ");
        return page;
    }

    private HtmlEditorPage saveChangesInExtraPopup(){
        new Element(saveChangesBtnInExtaLctr, "Save changes button in Extra popup")
                .click();
        waitSaving();
        extraBtn.click();
        return new HtmlEditorPage();
    }

    private HtmlEditorPage saveChangesInHtmlEditor(){
        saveChanges();
        return new HtmlEditorPage();
    }

    public HtmlEditorPage clearAndAddHtml(String html){
        htmlTextArea.clearAndSendKeys(html);
        return saveChangesInHtmlEditor();
    }

    public HtmlEditorPage addCSS(String css){
        cssTextArea.sendKeys(css);
        return saveChangesInHtmlEditor();
    }

    public String getFirstCssRow(){
        return new Element(firstCssRowLctr).getText();
    }

    public void waitTillCssSectionLoaded(){
        waitFactory().getCustomWait(15, 500)
                .until(ExpectedConditions.invisibilityOfElementWithText(firstCssRowLctr, ""));
    }

    public HtmlEditorPage uploadNewImage(String name){
        String filePath = TestArtifactsProvider.getImagesFilePath(name);
        openFilesPopup()
                .uploadFile(filePath);
        try {
            return new HtmlEditorPage();
        }catch (UnhandledAlertException e){
            new Alert()
                    .verifyAlertMsg(
                            "A file with the name \"" + name + "\" already exists on the server.\n" +
                            "Would you like to replace the existing file?")
                    .confirm();
            return new HtmlEditorPage();
        }
    }

    private FilesPopup openFilesPopup(){
        fileUploaderBtn.click();
        return new FilesPopup();
    }

    public String getFirstImageNameFromFiles(){
        return openFilesPopup()
                .getFirstFileName();
    }

    public SimpleEditorPage uploadFont(String fontName, String woffFile, String woff2File){
        return openFilesPopup()
                .clickUploadNewFont()
                .populateFields(fontName, woffFile, woff2File)
                .createFont()
                .switchToEditor();

    }


    private class FilesPopup {
        private final By firstFileNameFromFilesLctr = By.xpath("//ul[@class='editor-asset-list']/li[1]/*[@class='editor-asset-name']");
        private final By uploadNewImageLctr = By.xpath("//div[contains(@class, 'ac-editor-widget-file-uploader')]//div[contains(@class, 'asset-uploader')]/div[1]");
        private final By uploadNewFontLctr = By.xpath("//div[contains(@class, 'ac-editor-widget-file-uploader')]//div[contains(@class, 'asset-uploader')]/div[1]");



        private Element firstFileName = new Element(firstFileNameFromFilesLctr);
        private Element uploadNewImageBtn = new Element(uploadNewImageLctr, "Upload New Image");
        private Element uploadNewFontBtn = new Element(uploadNewFontLctr, "Upload fonts");

        private String getFirstFileName(){
            return firstFileName.getText();
        }

        private void uploadFile(String fileName){
            new Element(fileUploaderInputLctr, "Upload file input")
                    .sendKeys(fileName);
        }

        private CreateNewFontPopup clickUploadNewFont(){
            uploadNewFontBtn.click();
            return new CreateNewFontPopup();
        }




    }




}
