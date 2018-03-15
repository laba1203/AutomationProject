package talkable.talkableSite.campaign.pages.campaignNavigationMenu;

import abstractObjects.AbstractElementsContainer;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.editorPage.EditorPage;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;

public class CampaignNavigationMenuOnLaunchCampaignPage extends AbstractElementsContainer{

    private ElmntDetailsButton elmntDetailsButton;
    private ElmntRulesButton rulesButton;
    private ElmntPreviewButton elmntPreviewButton;
    private ElmntEditorButton elmntEditorButton;

    public CampaignNavigationMenuOnLaunchCampaignPage(){
//        initialization of containers:
//        initiateVisibleElements(containers);
        elmntDetailsButton = new ElmntDetailsButton();
        rulesButton = new ElmntRulesButton();
        elmntPreviewButton = new ElmntPreviewButton();
        elmntEditorButton = new ElmntEditorButton();
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
