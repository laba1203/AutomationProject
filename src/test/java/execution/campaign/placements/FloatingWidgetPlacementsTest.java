package execution.campaign.placements;

import common.cases.CommonScenarios;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.headerFrame.Header;
import util.DriverConfig;
import util.EnvFactory;
import util.logging.Log;

import static talkable.common.CampaignPlacement.FloatingWidget;
import static talkable.talkableSite.campaignsPage.Table.Status.DISABLED;

/*Link to scenario: https://docs.google.com/spreadsheets/d/1V4aAcERN0sENKasiep44-1GOiAunSm9qyAqXai6GNrA
* */
public class FloatingWidgetPlacementsTest extends BasePlacementsTest{
    private static final String fwCampaignName = "Invite Floating Widget_Placements testing";

    @Test
    public void test00_reactivateFwCampaign(){
        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(fwCampaignName, DISABLED);
        detailsPage.campaignNavigationMenu.clickLaunchButton().launchIntegratedCampaign();
    }

    @Test
    public void test01_openCampaignPlacementsPage(){
        new Header().openMenu().clickCampaignPlacementsButton();
    }

    @Test
    public void test02_addFwPlacement(){
        String inclusionText = "/" + page1;
        PageCampaignPlacements campaignPlacements = new PageCampaignPlacements()
                .updateFirstPlacementRow(FloatingWidget, true, inclusionText);
        campaignPlacements = campaignPlacements.waitTillChangesApplied();
        campaignPlacements.assertPlacement(FloatingWidget, true, inclusionText);
    }


    @Test
    public void test03_openPage1_onCustomerSite(){
        positiveVerificationOnSite(FloatingWidget, page1);
    }

    @Test
    public void test04_openPage2_onCustomerSite(){
        negativeVerificationOnSite(FloatingWidget, page2);
    }

    @Test
    public void test05_openPage3_onCustomerSite(){
        negativeVerificationOnSite(FloatingWidget, page3);
    }

    @BeforeGroups("add-second-placement")
    public void openTalkable(){
        driver.navigate().to(EnvFactory.getAdminUrl());
    }

    @Test(groups = "add-second-placement")
    public void test06_openCampaignPlacementsAndAddRegex(){
        PageCampaignPlacements campaignPlacements = CommonScenarios
                .addCampaignPlacement(FloatingWidget, true, true, page2);
        campaignPlacements.assertPlacement(FloatingWidget, true, page2);
    }

    @Test
    public void test07_openPage1_onCustomerSite(){
        positiveVerificationOnSite(FloatingWidget, page1);
    }

    @Test
    public void test08_openPage2_onCustomerSite(){
        positiveVerificationOnSite(FloatingWidget, page2);
    }

    @Test
    public void test09_openPage3_onCustomerSite(){
        negativeVerificationOnSite(FloatingWidget, page3);
    }

    @Test
    public void test10_removeCampaignPlacement(){
        openTalkable();
        PageCampaignPlacements campaignPlacements = new Header().openMenu()
                .clickCampaignPlacementsButton()
                .removePlacement(FloatingWidget, true, page2);
        try {
            campaignPlacements.assertPlacement(FloatingWidget, true, page2);
            Assert.fail("FAILED: Campaign placement is not deleted.");
        }catch (AssertionError e ){
            Log.testPassed("Campaign placement is deleted.");
        }
    }

    @AfterClass
    public void deactivateCampaign(){
        driver.navigate().refresh();
        new Header()
                .openCampaignsPage()
                .deactivateAllLiveCampaigns();
    }

}
