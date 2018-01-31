package talkable.IntegrationInstructionPage;

public class IntegrationInstructionPage {

    ElmntDontShowItAgain dontShowItAgainButton;
    ElmntCreateCampaignButton createCampaignButton;
    ElmntIntegrateTalkableButton integrateTalkableButton;

    public IntegrationInstructionPage(){
        dontShowItAgainButton = new ElmntDontShowItAgain();
        createCampaignButton = new ElmntCreateCampaignButton();
        integrateTalkableButton = new ElmntIntegrateTalkableButton();
    }

    public void dontShowItAgain(){
        dontShowItAgainButton.click();
    }

    public void clickIntegrateTkbl(){
        integrateTalkableButton.click();
    }

}