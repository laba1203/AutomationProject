package talkable.campaignNavigationMenu;

import abstractObjects.DrivenElement;
import talkable.campaignNavigationMenu.elements.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CampaignNavigationMenu {

    private DetailsButton detailsButton;
    private RulesButton rulesButton;
    private PreviewButton previewButton;
    private EditorButton editorButton;
    private CampaignStatusField campaignStatusField;
    private LaunchCampaignButton launchCampaignButton;

    DrivenElement[] elements = new DrivenElement[]{detailsButton};




    public CampaignNavigationMenu(){

//        initialization of elements:
        detailsButton = new DetailsButton();
        rulesButton = new RulesButton();
        previewButton = new PreviewButton();
        editorButton = new EditorButton();
        campaignStatusField = new CampaignStatusField();
        launchCampaignButton = new LaunchCampaignButton();

    }



}
