package talkable.talkableSite.campaign.pages.campaignEditorPage;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.campaign.pages.AbstractCampaignPage;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.SelectedViewField;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.localizationSidebar.LocalizationSidebar;
import talkable.talkableSite.campaign.pages.campaignEditorPage.elements.viewRecordsContainer.ViewRecordsContainer;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenuOnEditor;

public class EditorPage extends AbstractCampaignPage{

    public CampaignNavigationMenuOnEditor campaignNavigationMenu;
    private LocalizationSidebar localizationSidebar;

    private SelectedViewField selectedViewField;
    private ViewRecordsContainer viewRecords;




    public EditorPage(){

        this.campaignNavigationMenu = new CampaignNavigationMenuOnEditor();
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
