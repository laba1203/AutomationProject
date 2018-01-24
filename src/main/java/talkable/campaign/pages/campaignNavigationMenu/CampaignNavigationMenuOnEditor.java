package talkable.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElementsContainer;
import talkable.campaign.pages.campaignNavigationMenu.elements.*;

public class CampaignNavigationMenuOnEditor extends AbstractElementsContainer{

    public DetailsButton detailsButton;
    public RulesButton rulesButton;
    public PreviewButton previewButton;
    public EditorButton editorButton;
    private CampaignStatusField campaignStatusField;

    public CampaignNavigationMenuOnEditor(){

//        initialization of elements:
//        initiateVisibleElements(elements);
        detailsButton = new DetailsButton();
        rulesButton = new RulesButton();
        previewButton = new PreviewButton();
        editorButton = new EditorButton();
        campaignStatusField = new CampaignStatusField();
    }

    public String getCampaignStatus(){
        return campaignStatusField.getText();

    }




}
