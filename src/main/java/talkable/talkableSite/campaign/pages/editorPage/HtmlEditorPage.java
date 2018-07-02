package talkable.talkableSite.campaign.pages.editorPage;

public class HtmlEditorPage extends AbstractEditorPage{




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
