package talkable.talkableSite.campaign.pages;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;

public abstract class AbstractCampaignPage extends AbstractTalkableSitePage{

    public CampaignNavigationMenu campaignNavigationMenu;

    public AbstractCampaignPage(){
        campaignNavigationMenu = new CampaignNavigationMenu();

    }
}
