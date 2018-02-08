package talkable.campaign.pages.campaignEditorPage;

import abstractObjects.AbstractElementsContainer;
import talkable.campaign.pages.campaignEditorPage.elements.SelectedViewField;
import talkable.campaign.pages.campaignEditorPage.elements.viewRecordsContainer.ViewRecordsContainer;
import talkable.campaign.pages.campaignEditorPage.elements.localizationSidebar.LocalizationSidebar;
import talkable.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;

public class EditorPage extends AbstractElementsContainer{

    public CampaignNavigationMenuOnEditor campaignNavigationMenu;
    private LocalizationSidebar localizationSidebar;

    private SelectedViewField selectedViewField;
    private ViewRecordsContainer viewRecords;




    public EditorPage(){
        campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
        selectedViewField = new SelectedViewField();
        localizationSidebar = new LocalizationSidebar();
    }

    public void switchViewByIndex(int index){
        selectedViewField.click();
        new ViewRecordsContainer().selectViewByIndex(index);
        localizationSidebar = new LocalizationSidebar();
    }

    //Not work:
    public void switchViewByName(String name){
        selectedViewField.click();
        new ViewRecordsContainer().selectViewByName(name);
        localizationSidebar = new LocalizationSidebar();
    }



}
