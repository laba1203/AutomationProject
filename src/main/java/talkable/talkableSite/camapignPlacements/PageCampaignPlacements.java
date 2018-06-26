package talkable.talkableSite.camapignPlacements;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.common.CampaignPlacement;
import talkable.common.CommonMethods;
import talkable.talkableSite.AbstractTalkableSitePage;
import util.WaitFactory;
import util.logging.Log;

import java.util.ArrayList;

import static talkable.common.CampaignPlacement.*;

public class PageCampaignPlacements extends AbstractTalkableSitePage{
    private static final By createTileLctr = By.xpath("//*[text() = 'Create']");
    private static final String dropDownElementLctr = "//*[@class = 'dropdown-link' and text() = '";


    public PageCampaignPlacements(){
        //initiate element to ensure that the page is opened:
        new PlacementTile(FloatingWidget);
    }

    public PageCampaignPlacements addInclusion(CampaignPlacement placement, boolean regex, String inclusionText){
        PlacementTile placementTile = getPlacement(placement);
        placementTile.addInclusion(regex, inclusionText);
        waitSaving();
        return new PageCampaignPlacements();
    }

    public PageCampaignPlacements addExclusion(CampaignPlacement placement, boolean regex, String exclusionText){
        PlacementTile placementTile = getPlacement(placement);

        placementTile.addExclusion(regex, exclusionText);
        waitSaving();
        return new PageCampaignPlacements();
    }

    public PageCampaignPlacements updateFirstPlacementRow(CampaignPlacement placement, boolean isInclusion, String newValue){
        PlacementTile placementTile = getPlacement(placement);
        placementTile.updateFirstPlacement(isInclusion, newValue);
        waitSaving();
        return new PageCampaignPlacements();
    }

    public PageCampaignPlacements removePlacement(CampaignPlacement placement, boolean isInclusion, String placementText){
        PlacementTile placementTile = getPlacement(placement);
        placementTile.removePlacement(isInclusion, placementText);
        waitSaving();
        return new PageCampaignPlacements();
    }


    private ArrayList<String> getPlacementsList(CampaignPlacement placement, boolean inclusion){
        PlacementTile placementTile = getPlacement(placement);
        if(inclusion){
            return placementTile.getShownOnList();
        }
        else {
            return placementTile.getHiddenOnList();
        }
    }

    public boolean assertPlacement(CampaignPlacement placement, boolean isInclusion, String expectedPlacement){
        ArrayList<String> list = getPlacementsList(placement, isInclusion);
        for (String item :
                list) {
            if (item.equals(expectedPlacement)){
                Log.logRecord("Placement with text: <" + expectedPlacement + "> is displayed");
                return true;
            }
        }
        Log.logRecord("Placement with text: <" + expectedPlacement + "> is not found.\r\n Available placements: " + list.toString());
        return false;
    }

    public PageCampaignPlacements waitTillChangesApplied(){
        WaitTillChangesAppliedMsg warning = new WaitTillChangesAppliedMsg();
        for (int i = 0; i < 60; i++){
            try{
                new WaitTillChangesAppliedMsg();
                WaitFactory.getCustomWait(10, 1000)
                        .until(ExpectedConditions.invisibilityOf(warning.getWebElement()));
                break;
            }
            catch (TimeoutException ignored){
            }
        }
        return new PageCampaignPlacements();
    }

    public PageCampaignPlacements deletePlacementTileWithActivateCampaign(CampaignPlacement placement){
        getPlacement(placement).deleteTile();
        new CampaignsAttentionPopup().deactivate();
        return new PageCampaignPlacements();
    }

    private PlacementTile getPlacement(CampaignPlacement placement){
        return new PlacementTile(placement);
    }

//    public CampaignPlacement createNewPlacementTile(CampaignPlacement placement, String campaignToBeAttached){
//        String placementName = CommonMethods.getCampaignPlacementString(placement);
//        new Element(createTileLctr, "Create Placement Tile button")
//                .click();
//        getDropDownElement(placement).click();
//        new PopupEditPlacement().changePlacementName(placementName);
//        //todo: to be completed
//    }

    private Element getDropDownElement(CampaignPlacement placement){
        return new Element(By.xpath(
                dropDownElementLctr + CommonMethods.getCampaignPlacementString(placement) + "']"),
                CommonMethods.getCampaignPlacementString(placement) + " record"
        );
    }

    public PageCampaignPlacements deleteAllPlacements(CampaignPlacement placement){
        getPlacement(placement).deleteAllPlacements();
        waitSaving();
        return new PageCampaignPlacements();
    }



}
