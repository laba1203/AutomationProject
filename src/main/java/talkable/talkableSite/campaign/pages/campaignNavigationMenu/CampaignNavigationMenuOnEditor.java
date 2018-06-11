package talkable.talkableSite.campaign.pages.campaignNavigationMenu;

//import talkable.campaign.pages.campaignRulesPage.CampaignRulesPage;

public class CampaignNavigationMenuOnEditor extends CampaignNavigationMenuOnLaunchCampaignPage{

//    private ElmntDetailsButton elmntDetailsButton;
//    private ElmntRulesButton rulesButton;
//    private ElmntPreviewButton elmntPreviewButton;
//    private ElmntEditorButton elmntEditorButton;
    private ElmntCampaignStatusField elmntCampaignStatusField;

    public CampaignNavigationMenuOnEditor(){
//        initialization of containers:
//        initiateVisibleElements(containers);
//        elmntDetailsButton = new ElmntDetailsButton();
//        rulesButton = new ElmntRulesButton();
//        elmntPreviewButton = new ElmntPreviewButton();
//        elmntEditorButton = new ElmntEditorButton();
        elmntCampaignStatusField = new ElmntCampaignStatusField();
    }

    public String getCampaignStatus(){
        return elmntCampaignStatusField.getText();
    }

//    public SimpleEditorPage openEditorPage(){
//        elmntEditorButton.click();
//        return new SimpleEditorPage();
//    }
//
//    public CampaignDetailsPage openDetailsPage(){
//        elmntDetailsButton.click();
//        return new CampaignDetailsPage();
//    }
//
//    public PageCampaignRules openRulesPage(){
//        rulesButton.click();
//        return new PageCampaignRules();
//    }


}
