package execution.campaign.placements;

import common.cases.CommonScenarios;
import common.cases.functionalities.CampaignPlacementsScenarios;
import org.testng.Assert;
import org.testng.annotations.*;
import talkable.common.CampaignPlacement;
import talkable.talkableSite.camapignPlacements.PageCampaignPlacements;
import talkable.talkableSite.campaign.pages.detailsPage.CampaignDetailsPage;
import talkable.talkableSite.headerFrame.Header;
import util.DriverConfig;
import util.EnvFactory;
import util.logging.Log;

import static talkable.common.CampaignPlacement.FloatingWidget;
import static talkable.common.CampaignType.Invite;
import static talkable.talkableSite.campaignsPage.Table.Status.DISABLED;

/*Link to scenario: https://docs.google.com/spreadsheets/d/1V4aAcERN0sENKasiep44-1GOiAunSm9qyAqXai6GNrA
* */
public class FloatingWidgetPlacementsTest extends BasePlacementsTest{
    private static final String fwCampaignName = "Invite Floating Widget_Placements testing";


    @Test
    public void generalTest(){
        String placementValue = "/" + page1;

        CommonScenarios.navigateToAdminUrl();
        CampaignPlacementsScenarios.openCampaignPlacementsPage();
        CampaignPlacementsScenarios.deleteAllPlacements(FloatingWidget);

        //todo: should be completed (works fine till here)

        CampaignPlacementsScenarios.updateFirstPlacementRow(FloatingWidget, placementValue, true);
        //verify campaign is present on page1
        CampaignPlacementsScenarios.assertThatCampaignIsPresentOnSite(
                Invite,
                FloatingWidget,
                SITE_URL + page1
        );
        //verify campaign is NOT present on page2
        CampaignPlacementsScenarios.assertThatCampaignIsNotPresentOnSite(
                Invite,
                FloatingWidget,
                SITE_URL + page2
        );
        //verify campaign is NOT present on page3
        CampaignPlacementsScenarios.assertThatCampaignIsNotPresentOnSite(
                Invite,
                FloatingWidget,
                SITE_URL + page3
        );

        //add second Regex placement
        CommonScenarios.navigateToAdminUrl();
        CampaignPlacementsScenarios.openCampaignPlacementsPage();
        CampaignPlacementsScenarios.addNewPlacement(
                FloatingWidget,
                true,
                true,
                page2
        );
        CampaignPlacementsScenarios.assertPlacementOnCampaignPlacementsPage(
                FloatingWidget,
                true,
                page2
        );

        System.out.println("\r\n***** Cookies: ***** ");
        System.out.println(DriverConfig.getDriver().manage().getCookies());
        System.out.println("----- ---- ---- ---");
        System.out.println(DriverConfig.getDriver().manage().getCookies().toString());
        System.out.println("*** End ****\r\n");

        DriverConfig.getDriver().manage().deleteAllCookies();
        Log.debagRecord("All cookies deleted.");

        System.out.println("\r\n***** Cookies 2 : ***** ");
        System.out.println(DriverConfig.getDriver().manage().getCookies());
        System.out.println("----- ---- ---- ---");
        System.out.println(DriverConfig.getDriver().manage().getCookies().toString());
        System.out.println("*** End ****\r\n");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //verify campaign is present on page1
        CampaignPlacementsScenarios.assertThatCampaignIsPresentOnSite(
                Invite,
                FloatingWidget,
                SITE_URL + page1
        );

        System.out.println("\r\n***** Cookies 2 : ***** ");
        System.out.println(DriverConfig.getDriver().manage().getCookies());
        System.out.println("----- ---- ---- ---");
        System.out.println(DriverConfig.getDriver().manage().getCookies().toString());
        System.out.println("*** End ****\r\n");

        DriverConfig.getDriver().manage().deleteAllCookies();
        Log.debagRecord("All cookies deleted.");

        //verify campaign is  present on page2
        CampaignPlacementsScenarios.assertThatCampaignIsPresentOnSite(
                Invite,
                FloatingWidget,
                SITE_URL + page2
        );
        //verify campaign is NOT present on page3
        CampaignPlacementsScenarios.assertThatCampaignIsNotPresentOnSite(
                Invite,
                FloatingWidget,
                SITE_URL + page3
        );

        System.out.println("\r\n***** Cookies after only site visit: ***** ");
        System.out.println(DriverConfig.getDriver().manage().getCookies());
        System.out.println("----- ---- ---- ---");
        System.out.println(DriverConfig.getDriver().manage().getCookies().toString());
        System.out.println("*** End ****\r\n");

        DriverConfig.getDriver().manage().deleteAllCookies();
        Log.debagRecord("All cookies deleted.");

        CommonScenarios.navigateToAdminUrl();

        CommonScenarios.submitLoginForm(USER_EMAIL, EnvFactory.getPassword());
        System.out.println("\r\n***** Cookies after only Talkable visit: ***** ");
        System.out.println(DriverConfig.getDriver().manage().getCookies());
        System.out.println("----- ---- ---- ---");
        System.out.println(DriverConfig.getDriver().manage().getCookies().toString());
        System.out.println("*** End ****\r\n");

        CampaignPlacementsScenarios.openCampaignPlacementsPage();
        CampaignPlacementsScenarios.removePlacement(FloatingWidget, true, page2);

        CommonScenarios.navigateToAdminUrl();
        CommonScenarios.openCampaignsPage();
        CommonScenarios.deactivateAllCampaigns();
    }


//
//
//    @Test
//    public void test00_reactivateFwCampaign(){
//        CampaignDetailsPage detailsPage = new Header().openCampaignsPage().openCampaignByName(fwCampaignName, DISABLED);
//        detailsPage.campaignNavigationMenu.clickLaunchButton().launchIntegratedCampaign();
//    }
//
//    @Test
//    public void test01_openCampaignPlacementsPage(){
//        new Header().openMenu().clickCampaignPlacementsButton();
//    }
//
//    @Test
//    public void test02_addFwPlacement(){
//        String inclusionText = "/" + page1;
//        PageCampaignPlacements campaignPlacements = new PageCampaignPlacements()
//                .updateFirstPlacementRow(FloatingWidget, true, inclusionText);
//        campaignPlacements = campaignPlacements.waitTillChangesApplied();
//        campaignPlacements.assertPlacement(FloatingWidget, true, inclusionText);
//    }
//
//
//    @Test
//    public void test03_openPage1_onCustomerSite(){
//        positiveVerificationOnSite(FloatingWidget, page1);
//    }
//
//    @Test
//    public void test04_openPage2_onCustomerSite(){
//        negativeVerificationOnSite(FloatingWidget, page2);
//    }
//
//    @Test
//    public void test05_openPage3_onCustomerSite(){
//        negativeVerificationOnSite(FloatingWidget, page3);
//    }
//
//    @BeforeGroups("add-second-placement")
//    public void openTalkable(){
//        driver.navigate().to(EnvFactory.getAdminUrl());
//    }
//
////    @Test(groups = "add-second-placement")
////    public void test06_openCampaignPlacementsAndAddRegex(){
////        PageCampaignPlacements campaignPlacements = CommonScenarios
////                .addCampaignPlacement(FloatingWidget, true, true, page2);
////        campaignPlacements.assertPlacement(FloatingWidget, true, page2);
////    }
//
//    @Test
//    public void test07_openPage1_onCustomerSite(){
//        positiveVerificationOnSite(FloatingWidget, page1);
//    }
//
//    @Test
//    public void test08_openPage2_onCustomerSite(){
//        positiveVerificationOnSite(FloatingWidget, page2);
//    }
//
//    @Test
//    public void test09_openPage3_onCustomerSite(){
//        negativeVerificationOnSite(FloatingWidget, page3);
//    }
//
//
//    @Test
//    public void test10_removeCampaignPlacement(){
//        openTalkable();
//        PageCampaignPlacements campaignPlacements = new Header().openMenu()
//                .clickCampaignPlacementsButton()
//                .removePlacement(FloatingWidget, true, page2);
//        try {
//            campaignPlacements.assertPlacement(FloatingWidget, true, page2);
//            Assert.fail("FAILED: Campaign placement is not deleted.");
//        }catch (AssertionError e ){
//            Log.testPassed("Campaign placement is deleted.");
//        }
//    }
//
//    @AfterClass
//    public void deactivateCampaign(){
//        driver.navigate().refresh();
//        new Header()
//                .openCampaignsPage()
//                .deactivateAllLiveCampaigns();
//    }

}
