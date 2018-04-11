package talkable.talkableSite.camapignPlacements;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.common.CampaignPlacement;
import talkable.talkableSite.AbstractTalkableSitePage;
import util.DriverConfig;

import java.util.ArrayList;

import static talkable.common.CampaignPlacement.*;

public class PageCampaignPlacements extends AbstractTalkableSitePage{

    private PlacementTile postPurchaseSection = new PlacementTile(PostPurchase);
    private PlacementTile floatingWidgetSection = new PlacementTile(FloatingWidget);
    private PlacementTile standaloneSection = new PlacementTile(Standalone);
    private PlacementTile gleamSection = new PlacementTile(Gleam);

    public PageCampaignPlacements addInclusion(CampaignPlacement placement, boolean regex, String inclusionText){
        PlacementTile placementTile = getPlacement(placement);
        assert placementTile != null;
        placementTile.addInclusion(regex, inclusionText);
        waitSaving();
        return new PageCampaignPlacements();
    }

    public PageCampaignPlacements addExclusion(CampaignPlacement placement, boolean regex, String exclusionText){
        PlacementTile placementTile = getPlacement(placement);

        assert placementTile != null;
        placementTile.addExclusion(regex, exclusionText);
        waitSaving();
        return new PageCampaignPlacements();
    }

    public PageCampaignPlacements updateFirstPlacementRow(CampaignPlacement placement, boolean isInclusion, String newValue){
        PlacementTile placementTile = getPlacement(placement);
        assert placementTile != null;
        placementTile.updateFirstPlacement(isInclusion, newValue);
        waitSaving();
        return new PageCampaignPlacements();
    }

    public PageCampaignPlacements removePlacement(CampaignPlacement placement, boolean isInclusion, String placementText){
        PlacementTile placementTile = getPlacement(placement);
        assert placementTile != null;
        placementTile.removePlacement(isInclusion, placementText);
        waitSaving();
        return new PageCampaignPlacements();
    }



    private ArrayList<String> getPlacementsList(CampaignPlacement placement, boolean inclusion){
        PlacementTile placementTile = getPlacement(placement);
        assert placementTile != null;
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
                return true;
            }
        }
        Assert.fail("FAILED: Not found Placement with text: <" + expectedPlacement + ">.\r\n Available placements: " + list.toString());
        return false;
    }

    public PageCampaignPlacements waitTillChangesApplied(){
        WaitTillChangesAppliedMsg warning = new WaitTillChangesAppliedMsg();
        for (int i = 0; i < 60; i++){
            try{
                new WaitTillChangesAppliedMsg();
                DriverConfig.getCustomWait(10, 1000)
                        .until(ExpectedConditions.invisibilityOf(warning.getWebElement()));
                break;
            }
            catch (TimeoutException ignored){
            }
        }
        return new PageCampaignPlacements();
    }


    private PlacementTile getPlacement(CampaignPlacement placement){
        switch (placement){
            case FloatingWidget:
                return floatingWidgetSection;
            case PostPurchase:
                return postPurchaseSection;
            case Standalone:
                return standaloneSection;
            case Gleam:
                return gleamSection;
            default:
                Assert.fail("Unknown placement type: " + placement.toString());
                return null;
        }

    }

}
