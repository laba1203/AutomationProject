package talkable.campaignNavigationMenu;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.DrivenElement;
import org.openqa.selenium.NoSuchElementException;
import talkable.campaignNavigationMenu.elements.*;

public class CampaignNavigationMenu extends AbstractElementsContainer{

    public DetailsButton detailsButton;
    public RulesButton rulesButton;
    public PreviewButton previewButton;
    public EditorButton editorButton;
    private CampaignStatusField campaignStatusField;
    public LaunchCampaignButton launchCampaignButton;
    public DeactivateCampaignButton deactivateCampaignButton;

//    private DrivenElement[] elements = new DrivenElement[]{
//            detailsButton,
//            rulesButton,
//            previewButton,
//            editorButton,
//            campaignStatusField,
//            launchCampaignButton
//    };




    public CampaignNavigationMenu(){

//        initialization of elements:
//        initiateVisibleElements(elements);
        detailsButton = new DetailsButton();
        rulesButton = new RulesButton();
        previewButton = new PreviewButton();
        editorButton = new EditorButton();
        campaignStatusField = new CampaignStatusField();
        try{
            launchCampaignButton = new LaunchCampaignButton();
        }
        catch (NoSuchElementException e ){
            System.out.println(e);
            deactivateCampaignButton = new DeactivateCampaignButton();
        }
    }

    public String getCampaignStatus(){
        return campaignStatusField.getText();

    }




}
