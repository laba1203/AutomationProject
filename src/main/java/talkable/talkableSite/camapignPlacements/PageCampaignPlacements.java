package talkable.talkableSite.camapignPlacements;

import talkable.talkableSite.AbstractTalkableSitePage;

import static talkable.common.CampaignPlacement.*;

public class PageCampaignPlacements extends AbstractTalkableSitePage{

    public PlacementTile postPurchaseSection = new PlacementTile(PostPurchase);
    public PlacementTile floatingWidgetSection = new PlacementTile(FloatingWidget);
    public PlacementTile standaloneSection = new PlacementTile(Standalone);
    public PlacementTile gleamSection = new PlacementTile(Gleam);

//    public PageCampaignPlacements addInclusion(CampaignPlacement placement, boolean regex, String inclusionText){
//        PlacementTile placementTile = getPlacement(placement);
//        assert placementTile != null;
//        return placementTile.edit().add(true, regex, inclusionText);
//    }

//    public PageCampaignPlacements addExclusion(CampaignPlacement placement, boolean regex, String exclusionText){
//        PlacementTile placementTile = getPlacement(placement);
//        assert placementTile != null;
//        return placementTile.edit().add(false, regex, exclusionText);
//    }
//
//
//    private PlacementTile getPlacement(CampaignPlacement placement){
//        switch (placement){
//            case FloatingWidget:
//                return floatingWidgetSection;
//            case PostPurchase:
//                return postPurchaseSection;
//            case Standalone:
//                return standaloneSection;
//            case Gleam:
//                return gleamSection;
//            default:
//                Assert.fail("Unknown placement type: " + placement.toString());
//                return null;
//        }
//
//    }


}
