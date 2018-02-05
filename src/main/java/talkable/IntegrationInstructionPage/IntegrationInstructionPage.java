package talkable.IntegrationInstructionPage;

import talkable.headerFrame.Header;

public class IntegrationInstructionPage {

    public Header header;
    private ElmntDontShowItAgain dontShowItAgainButton;
    private ElmntCreateCampaignButton createCampaignButton;
    private ElmntIntegrateTalkableButton integrateTalkableButton;

    public IntegrationInstructionPage(){
        header = new Header();
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