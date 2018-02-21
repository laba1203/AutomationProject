package talkable.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElementsContainer;
import talkable.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.campaign.pages.campaignEditorPage.EditorPage;
import talkable.campaign.pages.campaignRulesPage.PageCampaignRules;
//import talkable.campaign.pages.campaignRulesPage.CampaignRulesPage;

public class CampaignNavigationMenuOnEditor extends AbstractElementsContainer{

    private ElmntDetailsButton elmntDetailsButton;
    private ElmntRulesButton rulesButton;
    private ElmntPreviewButton elmntPreviewButton;
    private ElmntEditorButton elmntEditorButton;
    private ElmntCampaignStatusField elmntCampaignStatusField;

    public CampaignNavigationMenuOnEditor(){
//        initialization of containers:
//        initiateVisibleElements(containers);
        elmntDetailsButton = new ElmntDetailsButton();
        rulesButton = new ElmntRulesButton();
        elmntPreviewButton = new ElmntPreviewButton();
        elmntEditorButton = new ElmntEditorButton();
        elmntCampaignStatusField = new ElmntCampaignStatusField();
    }

    public String getCampaignStatus(){
        return elmntCampaignStatusField.getText();
    }

    public EditorPage openEditorPage(){
        elmntEditorButton.click();
        return new EditorPage();
    }

    public CampaignDetailsPage openDetailsPage(){
        elmntDetailsButton.click();
        return new CampaignDetailsPage();
    }

    public PageCampaignRules openRulesPage(){
        rulesButton.click();
        return new PageCampaignRules();
    }


}
