package talkable.talkableSite.camapignPlacements;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.CampaignPlacement;

import static talkable.talkableSite.campaign.pages.CampaignPlacement.*;

public class PageCampaignPlacements extends AbstractTalkableSitePage{

    public PlacementTile postPurchaseSection = new PlacementTile(PostPurchase);
    public PlacementTile floatingWidgetSection = new PlacementTile(FloatingWidget);
    public PlacementTile standaloneSection = new PlacementTile(Standalone);
    public PlacementTile gleamSection = new PlacementTile(Gleam);


}
