package talkable.talkableSite.createNewCampaignPage;

import abstractObjects.DrivenElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.CampaignType;
import talkable.talkableSite.createNewCampaignPage.elements.*;
import util.DriverConfig;

public class CreateNewCampaignPage extends AbstractTalkableSitePage {

//    WebDriverWait wait = new DriverConfig().getExplicitWait();
    private static final String title = "Choose New Campaign To Select | Talkable";


//   Web Elements visible after uploading:
    private SelectAdvocateDashboardButton selectAdvocateDashboardButton;
    private SelectInviteButton selectInviteButton;
    private SelectRewardGleamButton selectRewardGleamButton;

//    WebElements visible after some actions:
    private CreateFloatingWidgetButton createFloatingWidgetButton;
    private CreatePostPurchaseButton createPostPurchaseButton;
    private CreateStandaloneButton createStandaloneButton;


    public CreateNewCampaignPage(){
        isPageOpened(title);

        //initialization of visible WebElements:
        selectAdvocateDashboardButton = new SelectAdvocateDashboardButton();
        selectInviteButton = new SelectInviteButton();
        selectRewardGleamButton = new SelectRewardGleamButton();
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
                createPostPurchaseButton = new CreatePostPurchaseButton();
                createButton = createPostPurchaseButton;
                break;

            case FloatingWidget:
                createFloatingWidgetButton = new CreateFloatingWidgetButton();
                createButton = createFloatingWidgetButton;
                break;

            case Standalone:
                createStandaloneButton = new CreateStandaloneButton();
                createButton =  createStandaloneButton;
                break;

            case Gleam:
                //to be described
                Assert.fail("!!!    FAILED: Gleam button is not yet defined in CreateNewCampaignPage() !!!!!!!");
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
