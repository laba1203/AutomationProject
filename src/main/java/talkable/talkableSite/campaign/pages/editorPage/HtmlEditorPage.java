package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.common.elements.alert.Alert;

public class HtmlEditorPage extends AbstractEditorPage{

    private static final By extraBtnLctr = By.xpath("//*[@data-editor-toggle = 'extra-fields']");
    private static final By viewDestroyedMcsgLctr = By.xpath("//div[contains(text(), 'destroyed')]");

    private Element extraBtn = new Element(extraBtnLctr, "Extra button");


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
        new Element(viewDestroyedMcsgLctr);
        return new HtmlEditorPage();
    }




}
