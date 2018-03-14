package talkable.talkableSite.campaign.pages.campaignEditorPage;


import talkable.talkableSite.AbstractTkblSitePageWithoutHeader;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.SelectedViewField;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar.LocalizationSidebar;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;

public class EditorPage extends AbstractTkblSitePageWithoutHeader{

    public CampaignNavigationMenuOnEditor campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
    public LocalizationSidebar localizationSidebar = new LocalizationSidebar();
    public ElmntSaveButton saveButton = new ElmntSaveButton();
    private SelectedViewField selectedViewField = new SelectedViewField();


    public EditorPage(){
//        this.campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
//        selectedViewField = new SelectedViewField();
//        localizationSidebar = new LocalizationSidebar();
    }

    public EditorPage switchViewByIndex(int index){
        selectedViewField.click();
        new ContainerViewRecords().selectByIndex(index);
        return new EditorPage();
    }

    public EditorPage switchViewByName(String name){
        selectedViewField.click();
        new ContainerViewRecords().selectViewByText(name);
        return new EditorPage();
    }

    public EditorPage updateCopyLocalization(String localizationName, String newValue){
        localizationSidebar.setCopyLocalization(localizationName, newValue);
        return saveChanges();
    }

    public EditorPage saveChanges(){
        saveButton.click();
        waitSaving();
        return new EditorPage();
    }





}
