package execution.campaign.placements;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.headerFrame.Header;

import static talkable.talkableSite.campaignsPage.Table.Status.DISABLED;

public class FloatingWidgetPlacementsTest extends BasePlacementsTest{
    private static final String fwCampaignName = "";

    @BeforeClass
    public void reactivateFwCampaign(){
        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(fwCampaignName, DISABLED);
        detailsPage.campaignNavigationMenu.clickLaunchButton().launchIntegratedCampaign();
    }

    @Test
    public void test1_openCampaignPlacementsPage(){
        new Header().openMenu().clickCampaignPlacementsButton();
    }

    @Test
    public void test2_addFwPlacement(){}


}
