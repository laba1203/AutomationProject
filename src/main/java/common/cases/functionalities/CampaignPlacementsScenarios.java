package common.cases.functionalities;

import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import org.testng.Assert;
import talkable.common.CampaignPlacement;
import talkable.common.CampaignType;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.camapignPlacements.PlacementTile;
import talkable.talkableSite.headerFrame.Header;
import util.logging.Log;


public class CampaignPlacementsScenarios extends CommonScenarios{

    public static void openCampaignPlacementsPage(){
        new Header().openMenu().clickCampaignPlacementsButton();
        Log.logRecord("Campaign Placements page is opened");
    }

    /*Scenarios to add Campaign Placement(inclusion or exclusion).
     * Precondition: Header should be available.
     * 1. Open Campaign Placement page
     * 2. Add inclusion oe exclusion (as per @isInclusion)
     * 3. wait till changes applied
     * @Returns: PageCampaignPlacements for mentioned parameters.
     * */
    public static PageCampaignPlacements addNewPlacement(CampaignPlacement placement,
                                                              boolean isInclusion,
                                                              boolean regex,
                                                              String placementText) {
        PageCampaignPlacements campaignPlacements; //= new Header().openMenu().clickCampaignPlacementsButton();
        if (isInclusion) {
            campaignPlacements = new PageCampaignPlacements().addInclusion(placement, regex, placementText);
        } else {
            campaignPlacements = new PageCampaignPlacements().addExclusion(placement, regex, placementText);
        }
//        campaignPlacements = campaignPlacements.waitTillChangesApplied();
        Log.logRecord("<" +placement + "> campaign placement has been added. Regex = <" + regex + ">, IsInclusion = <" + isInclusion + ">, placement value = <" + placementText + ">.");
        return campaignPlacements;
    }

    public static void updateFirstPlacementRow(CampaignPlacement placement, String placementValue, boolean isInclusion){
        PageCampaignPlacements campaignPlacements = new PageCampaignPlacements()
                .updateFirstPlacementRow(placement, isInclusion, placementValue);
//        campaignPlacements = campaignPlacements.waitTillChangesApplied();
        campaignPlacements.assertPlacement(placement, isInclusion, placementValue);
        Log.logRecord("New placement is added for <" + placement + ">; placement value <" + placementValue + ">, isInclusion = " + isInclusion + ".");
    }

    public static void removePlacement(CampaignPlacement placement, boolean isInclusion, String page){
        new PageCampaignPlacements().removePlacement(placement, isInclusion, page);
        boolean isPlacementPresent = new PageCampaignPlacements()
                .assertPlacement(placement, isInclusion, page);
        Assert.assertEquals(isPlacementPresent, false, "FAILED: <" + placement + "> Campaign placement is still displayed on the Campaign Placements page when it was deleted.");
        Log.testPassed("<" + placement + "> campaign placement is deleted.");
    }

    public static void assertPlacementOnCampaignPlacementsPage(CampaignPlacement placement, boolean isInclusion, String expectedValue){
        boolean isPlacementPresent = new PageCampaignPlacements()
                .assertPlacement(placement, isInclusion, expectedValue);
        Assert.assertEquals(isPlacementPresent, true, "FAILED: Not found Placement with text: <" + expectedValue + ">");
        Log.testPassed("<" + placement + "> campaign is displayed on the Campaign Placements page (isInclusion = " + isInclusion + ").");
    }


    public static void assertThatCampaignIsPresentOnSite(CampaignType campaignType, CampaignPlacement placement, String siteLink){
        if(ClientSiteScenarios.isCampaignOnCustomerSite(campaignType, placement, siteLink)){
            Log.testPassed("<" + placement + "> campaign is displayed on the site page: " + siteLink  + " .");
        }else {
            Assert.fail("FAILED: Campaign <" + placement + "> is not found on page: " + siteLink);
        }
    }

    public static void assertThatCampaignIsNotPresentOnSite(CampaignType campaignType, CampaignPlacement placement, String siteLink){
        if(ClientSiteScenarios.isCampaignOnCustomerSite(campaignType, placement, siteLink)){
            Assert.fail("FAILED: Campaign <" + placement + "> is present on page: " + siteLink);
        }else {
            Log.testPassed("<" + placement + "> campaign is NOT displayed on the site page: " + siteLink  + " .");
        }
    }

    public static void deletePlacementTileWithActiveCampaign(CampaignPlacement placement){
        new PageCampaignPlacements().deletePlacementTileWithActivateCampaign(placement);
        Log.logRecord("Placement tile is deleted <" + placement + ">.") ;
    }

    //Doesn't work correctly
    public static void deleteAllPlacements(CampaignPlacement placement){
        new PageCampaignPlacements()
                .deleteAllPlacements(placement)
                .waitTillChangesApplied();
        Log.logRecord("All Placements records deleted");
    }





}
