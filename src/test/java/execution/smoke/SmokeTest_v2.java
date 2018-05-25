

package execution.smoke;

import common.cases.ClientSiteScenarios;
import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.CouponCodeType;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.IncentiveType;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;

import static talkable.common.CampaignPlacement.FloatingWidget;
import static talkable.common.CampaignType.Invite;


public class SmokeTest_v2 extends BaseTest{

    private static final String SITE_URL = PropertyLoader.loadEnvProperty("test.sites.smoke.test");
    private static final String siteName = PropertyLoader.loadProperty("sites.name.smokeTest");

    private static final String liveStatusActive = "Status: Live";
    private static final String liveStatusTest = "Status: Test";
//    private static final String liveStatusDisabled = "Status: Disabled";
    private static final String expectedAdvocateOffersCount = "Total: 1";
    private static final String campaignName = "AUTO_TEST_" + TestDataGenerator.getRandomId();

    /*Link to test scenario: https://drive.google.com/open?id=1rnq3vo9qQ25vtTwPF7hwXRt7zMBiK28VuAyPc50_X7s
    * */

    @Test
    public void createAndActivateCampaign(){
        //1. Login to Talkable.
        CommonScenarios.login(
                EnvFactory.getCommonUser(),
                EnvFactory.getPassword()
        );
        CommonScenarios.switchToIntegratedSiteByVisibleText(siteName);

        // 2. Verify site Name
        Assert.assertEquals(
                CommonScenarios.getSiteNameFromHeader(),
                siteName,
                "FAILED: Incorrect site name"
        );
        // Deactivate all campaigns
        CommonScenarios.deactivateAllCampaigns();


        // 3. Add new Campaign (Type = FW )
        CommonScenarios.initiateCampaignCreation(Invite, FloatingWidget);
        Assert.assertEquals(CommonScenarios.getCampaignStatusFromMenu(), liveStatusTest, "FAILED: Incorrect campaign status");

        // 4. Navigate to Rules page
        // 5. Modify Campaign name
        CommonScenarios.openCampaignRulesPage();
        CommonScenarios.setCampaignNameOnRulesPage(campaignName);

        // 6. Set Campaign description
        String campaignDesc = "Campaign for smoke test";
        CommonScenarios.setCampaignDescriptionOnRulesPage(campaignDesc);

        // 7. Set Advocate/Friend Offer deadline
        String advocateOfferDeadlineDate = "10/18/2020";
        String endTime = "05:48";
        String friendDeadline = "10/21/2020";
        CommonScenarios.setDeadlinesOnRulesPage(
                advocateOfferDeadlineDate,
                endTime,
                friendDeadline,
                endTime);

        // 8. Add incentive (Type = Sign Up)
        addIncentive();

        // 11. Launch Campaign
        CommonScenarios.launchCampaign();
        Assert.assertEquals(CommonScenarios.getCampaignStatusFromMenu(), liveStatusActive, "FAILED: Campaign is not activated");

        // 12. Create test offer
        CommonScenarios.createTestOfferNewAffiliateMember("test" + System.currentTimeMillis() + "@test.com");
        Assert.assertEquals(
                CommonScenarios.getAdvocateOfferTotalCountFromCampaignDetailsPage(),
                expectedAdvocateOffersCount,
                "FAILED: Incorrect Offers count on Campaign Details page");

        // 13. Verify campaign on site
        if(ClientSiteScenarios.isCampaignOnCustomerSite(Invite, FloatingWidget, SITE_URL)){
            Log.testPassed("Campaign is present on Customer site.\r\n");
        }else {
            Assert.fail("FAILED: Advocate trigger widget is not displayed on Customer Site.\r\n");
        }

    }

    @Test(dependsOnMethods = "createAndActivateCampaign")
    public void deactivateCampaign(){
        //Open Talkable
        CommonScenarios.navigateToAdminUrl();
        CommonScenarios.deactivateAllCampaigns();

        //Verify that campaign is not displayed on site
        if(ClientSiteScenarios.isCampaignOnCustomerSite(Invite, FloatingWidget, SITE_URL)){
            Assert.fail("FAILED: Floating Widget is displayed after deactivation. Test Failed");
        }else {
            Log.testPassed("Campaign is present on Customer site.\r\n");
        }
    }


    private void addIncentive() {
        PageCampaignRules rulesPage = new PageCampaignRules();
        rulesPage = rulesPage.createNewIncentive(IncentiveType.AdvocateSignupIncentive, 25, DiscountType.FixedAmount, CouponCodeType.MultiUse);
        rulesPage.createNewIncentive(IncentiveType.FriendIncentive_NewCustomer, 10, DiscountType.Percentage, CouponCodeType.MultiUse);
    }

}