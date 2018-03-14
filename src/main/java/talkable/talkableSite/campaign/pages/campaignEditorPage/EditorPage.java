package talkable.talkableSite.campaign.pages.campaignEditorPage;


import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.SelectedViewField;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar.LocalizationSidebar;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;

public class EditorPage extends AbstractElementsContainer{

    public CampaignNavigationMenuOnEditor campaignNavigationMenu;
    public LocalizationSidebar localizationSidebar;

    private SelectedViewField selectedViewField;


    public EditorPage(){
        this.campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
        selectedViewField = new SelectedViewField();
        localizationSidebar = new LocalizationSidebar();
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





}
