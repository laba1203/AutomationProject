package talkable.talkableSite.createNewCampaignPage;

import abstractObjects.DrivenElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.CampaignType;
import talkable.talkableSite.createNewCampaignPage.elements.*;


public class CreateNewCampaignPage extends AbstractTalkableSitePage {

//    private static final String title = "Choose New Campaign To Select | Talkable";

    private SelectAdvocateDashboardButton selectAdvocateDashboardButton = new SelectAdvocateDashboardButton();;
    private SelectInviteButton selectInviteButton = new SelectInviteButton();
    private SelectRewardGleamButton selectRewardGleamButton = new SelectRewardGleamButton();

    public CreateNewCampaignPage(){
//        selectAdvocateDashboardButton = new SelectAdvocateDashboardButton();
//        selectInviteButton = new SelectInviteButton();
//        selectRewardGleamButton = new SelectRewardGleamButton();
    }

    public void createCampaign(CampaignType campaignType,CampaignPlacement placementType){
        DrivenElement campaignButton = getSelectCampaignButton(campaignType);
        campaignButton.click();
        DrivenElement createButton = getPlacementButton(placementType);
        wait.until(ExpectedConditions.visibilityOf(createButton.getWebElement()));
        createButton.click();
    }


    private DrivenElement getPlacementButton(CampaignPlacement placementType){
        DrivenElement createButton = null;
        switch (placementType){
            case PostPurchase:
                createButton = new CreatePostPurchaseButton();
                break;

            case FloatingWidget:
                createButton = new CreateFloatingWidgetButton();
                break;

            case Standalone:
                createButton =  new CreateStandaloneButton();
                break;

            case Gleam:
                createButton = new ElmntCreateGleamButton();
                break;
        }
        return createButton;
    }

    private DrivenElement getSelectCampaignButton(CampaignType campaignType){
        DrivenElement campaignButton = null;
        switch(campaignType){
            case Invite:
                campaignButton = selectInviteButton;
                break;

            case AdvocateDashboard:
                campaignButton = selectAdvocateDashboardButton;
                break;

            case RewardGleam:
                campaignButton = selectRewardGleamButton;
                break;
        }
        return campaignButton;
    }
}
