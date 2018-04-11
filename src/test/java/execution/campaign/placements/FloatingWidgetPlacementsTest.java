package execution.campaign.placements;

import common.cases.CommonScenarios;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.headerFrame.Header;
import util.EnvFactory;

import static talkable.common.CampaignPlacement.FloatingWidget;
import static talkable.talkableSite.campaignsPage.Table.Status.DISABLED;

/*Link to scenario: https://docs.google.com/spreadsheets/d/1V4aAcERN0sENKasiep44-1GOiAunSm9qyAqXai6GNrA
* */
public class FloatingWidgetPlacementsTest extends BasePlacementsTest{
    private static final String fwCampaignName = "Invite Floating Widget_Placements testing";

    @Test
    public void test0_reactivateFwCampaign(){
        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(fwCampaignName, DISABLED);
        detailsPage.campaignNavigationMenu.clickLaunchButton().launchIntegratedCampaign();
    }

    @Test
    public void test1_openCampaignPlacementsPage(){
        new Header().openMenu().clickCampaignPlacementsButton();
    }

    @Test
    public void test2_addFwPlacement(){
        String inclusionText = "/" + page1;
        PageCampaignPlacements campaignPlacements = new PageCampaignPlacements()
                .updateFirstPlacementRow(FloatingWidget, true, inclusionText);
//                .addInclusion(FloatingWidget, false, inclusionText);

        campaignPlacements = campaignPlacements.waitTillChangesApplied();

        campaignPlacements.assertPlacement(FloatingWidget, true, inclusionText);
    }

    @Test
    public void test3_openPage1_onCustomerSite(){
        positiveVerificationOnSite(FloatingWidget, page1);
    }

    @Test
    public void test4_openPage2_onCustomerSite(){
        negativeVerificationOnSite(FloatingWidget, page2);
    }

    @Test
    public void test5_openPage3_onCustomerSite(){
        negativeVerificationOnSite(FloatingWidget, page3);
    }

    @BeforeGroups("add-second-placement")
    public void openTalkable(){
        driver.navigate().to(EnvFactory.getAdminUrl());
    }

    @Test(groups = "add-second-placement")
    public void test6_openCampaignPlacementsAndAddRegex(){
        PageCampaignPlacements campaignPlacements = CommonScenarios
                .addCampaignPlacement(FloatingWidget, true, true, page2);
        campaignPlacements.assertPlacement(FloatingWidget, true, page2);
    }

    @Test
    public void test7_openPage1_onCustomerSite(){
        positiveVerificationOnSite(FloatingWidget, page1);
    }

    @Test
    public void test8_openPage2_onCustomerSite(){
        positiveVerificationOnSite(FloatingWidget, page2);
    }

    @Test
    public void test9_openPage3_onCustomerSite(){
        negativeVerificationOnSite(FloatingWidget, page3);
    }

    @AfterClass
    public void removePlacementAndDeactivateCampaign(){
        openTalkable();
        PageCampaignPlacements campaignPlacements = new Header().openMenu()
                .clickCampaignPlacementsButton()
                .removePlacement(FloatingWidget, true, page2);
        campaignPlacements.header.openCampaignsPage().deactivateAllLiveCampaigns();
    }

}
