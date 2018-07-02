package talkable.talkableSite.campaign.pages.editorPage;

import abstractObjects.Element;
import org.openqa.selenium.By;

public class HtmlEditorPage extends AbstractEditorPage{

    private static final By extraBtnLctr = By.xpath("//*[@data-editor-toggle = 'extra-fields']");

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



}
