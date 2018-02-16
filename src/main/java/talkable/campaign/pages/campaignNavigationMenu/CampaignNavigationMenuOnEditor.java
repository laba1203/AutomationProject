package talkable.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElementsContainer;
import talkable.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.campaign.pages.campaignEditorPage.EditorPage;
import talkable.campaign.pages.campaignNavigationMenu.elements.*;
import talkable.campaign.pages.campaignRulesPage.PageCampaignRules;
//import talkable.campaign.pages.campaignRulesPage.CampaignRulesPage;

public class CampaignNavigationMenuOnEditor extends AbstractElementsContainer{

    private DetailsButton detailsButton;
    private RulesButton rulesButton;
    private PreviewButton previewButton;
    private EditorButton editorButton;
    private CampaignStatusField campaignStatusField;

    public CampaignNavigationMenuOnEditor(){
//        initialization of containers:
//        initiateVisibleElements(containers);
        detailsButton = new DetailsButton();
        rulesButton = new RulesButton();
        previewButton = new PreviewButton();
        editorButton = new EditorButton();
        campaignStatusField = new CampaignStatusField();
    }

    public String getCampaignStatus(){
        return campaignStatusField.getText();
    }

    public EditorPage openEditorPage(){
        editorButton.click();
        return new EditorPage();
    }

    public CampaignDetailsPage openDetailsPage(){
        detailsButton.click();
        return new CampaignDetailsPage();
    }

    public PageCampaignRules openRulesPage(){
        rulesButton.click();
        return new PageCampaignRules();
    }


}
