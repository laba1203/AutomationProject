

package execution.smoke;

import common.cases.CommonScenarios;
import customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.CampaignPlacement;
import talkable.talkableSite.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.talkableSite.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.CouponCodeType;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.IncentiveType;
import talkable.talkableSite.campaignsPage.PageCampaigns;
import talkable.talkableSite.createNewCampaignPage.CreateNewCampaignPage.CampaignType;
import talkable.talkableSite.headerFrame.Header;
import util.DriverConfig;

public class SmokeTest {
    DriverConfig driverFactory;
    WebDriver driver;
    private static final String siteUrl = "http://learn.talkable.com/QA-Max/void/automation-smoke-test/index.html";
    private static final String talkableUrl = "https://void.talkable.com";
    private static final String user = "maxim.laba+automation.smoke.test@talkable.com";
    private static final String password = "Password@1";
    private static final String siteName = "automation-smoke-test";
    private static final String liveStatusActive = "Status: Live";
    private static final String liveStatusTest = "Status: Test";
    private static final String liveStatusDisabled = "Status: Disabled";
    private static final String expectedAdvocateOffersCount = "Total: 1";
    private static final String campaignName = "AUTO_TEST_" + System.currentTimeMillis();

    /*Link to test scenario: https://drive.google.com/open?id=1rnq3vo9qQ25vtTwPF7hwXRt7zMBiK28VuAyPc50_X7s
    * */
    public SmokeTest() {
    }

    @BeforeClass
    public void setup() {
        this.driverFactory = new DriverConfig();
        this.driver = this.driverFactory.getDriver();
        this.driver.navigate().to("https://void.talkable.com");
    }

// 1. Login to Talkable.
    @Test
    public void test01_login() {
        CommonScenarios.login("maxim.laba+automation.smoke.test@talkable.com", "Password@1");
    }

// 2. Verify site Name
    @Test
    public void test02_verifySiteName() {
        Header header = new Header();
        Assert.assertEquals(header.getSiteName(), "automation-smoke-test", "FAILED: Incorrect site name");
    }

// 3. Add new Campaign (Type = FW )
    @Test
    public void test03_createNewCampaign() {
        CampaignDetailsPage detailsPage = CommonScenarios.initiateCampaignCreation(CampaignType.Invite, CampaignPlacement.FloatingWidget);
        Assert.assertEquals(detailsPage.campaignNavigationMenu.getCampaignStatus(), "Status: Test", "FAILED: Incorrect campaign status");
    }

// 4. Navigate to Rules page
// 5. Modify Campaign name
    @Test
    public void test04_setCampaignNameOnRulesPage() {
        String name = campaignName;
        CampaignDetailsPage detailsPage =  new CampaignDetailsPage();

        PageCampaignRules rulesPage = detailsPage.campaignNavigationMenu.openRulesPage();

        rulesPage = rulesPage.setCampaignName(name);
        Assert.assertEquals(rulesPage.getCampaignName(), name, "FAILED: Campaign Name is not updated");
    }

// 6. Modify Campaign description
    @Test
    public void test05_setCampaignDescription() {
        String description = "Campaign for smoke test";
        PageCampaignRules rulesPage = new PageCampaignRules();
        rulesPage = rulesPage.setCampaignDescription(description);
        Assert.assertEquals(rulesPage.getCampaignDescription(), description, "FAILED: Campaign description is not updated");
    }

// 7. Set Advocate/Friend Offer deadline
    @Test
    public void test06_setDeadlineDates() {
        String advocateOfferDeadlineDate = "10/18/2020";
        String endTime = "05:48";
        String friendDeadline = "10/21/2020";
        PageCampaignRules rulesPage = new PageCampaignRules();
        rulesPage = rulesPage.setDeadlineDates(advocateOfferDeadlineDate, endTime, friendDeadline, endTime);

        Assert.assertEquals(rulesPage.getAdvocateDeadlineDate(), advocateOfferDeadlineDate, "FAILED: Incorrect Advocate Deadline Date");
        Assert.assertEquals(rulesPage.getAdvocateDeadlineTime(), endTime, "FAILED: Incorrect Advocate Deadline Time");

        Assert.assertEquals(rulesPage.getFriendDeadlineDate(), friendDeadline, "FAILED: Incorrect Friend Deadline Date");
        Assert.assertEquals(rulesPage.getFriendDeadlineTime(), endTime,  "FAILED: Incorrect Friend Deadline Time");
    }

// 8. Add incentive (Type = Sign Up)
    @Test
    public void test07_addIncentive() {
        PageCampaignRules rulesPage = new PageCampaignRules();
        rulesPage = rulesPage.createNewIncentive(IncentiveType.AdvocateSignupIncentive, 25, DiscountType.FixedAmount, CouponCodeType.MultiUse);
        rulesPage = rulesPage.createNewIncentive(IncentiveType.FriendIncentive_NewCustomer, 10, DiscountType.Percentage, CouponCodeType.MultiUse);
    }

     /* To be added:
     9. Modify campaign in Editor

     10. Modify campaign placement
     */

// 11. Launch Campaign
    @Test
    public void test08_launchCampaign() {
        CampaignDetailsPage detailsPage = CommonScenarios.launchCampaign();
        Assert.assertEquals(detailsPage.campaignNavigationMenu.getCampaignStatus(), "Status: Live", "FAILED: Campaign is not activated");
    }

// 12. Create test offer.
    //!!! To be tested!! Doesn't work properly.
    @Test
    public void test09_createTestOffer() {
        CampaignDetailsPage detailsPage = CommonScenarios.createTestOfferForNonPostPurchase("test" + System.currentTimeMillis() + "@test.com");
        Assert.assertEquals(detailsPage.getAdvocateOffersTotalCount(), "Total: 1", "FAILED: Incorrect Offers count");
    }

    /*
    To be added:
    13. Verify that offer available in report.
    */


// 14. Verify FW on client site
    @Test
    public void test10_verifyCampaignOnSite() {
        this.driverFactory.getDriver().navigate().to("http://learn.talkable.com/QA-Max/void/automation-smoke-test/index.html");
        AdvocateTriggerWidgetFrame triggerWidget = new AdvocateTriggerWidgetFrame();
        triggerWidget.click();
    }

    @Test
    public void test11_openTalkable() {
        this.driver.navigate().to("https://admin.void.talkable.com");
    }

// 15. Deactivate campaign.
    @Test
    public void test12_deactivateCampaign() {
        PageCampaigns campaignsPage = (new Header()).clickCampaignsPage();
        CampaignDetailsPage detailsPage = campaignsPage.clickCampaignByName(campaignName);
        CampaignNavigationMenu menu = detailsPage.campaignNavigationMenu.deactivateCampaign();
        Assert.assertEquals(menu.getCampaignStatus(), "Status: Disabled", "FAILED: Campaign is not deactivated");
    }

// 16. Check on the site that FW is not shown
    @Test
    public void test13_verifyFwOnSite() {
        try {
            this.driverFactory.getDriver().navigate().to("http://learn.talkable.com/QA-Max/void/automation-smoke-test/index.html");
            new AdvocateTriggerWidgetFrame();
            Assert.fail("FAILED: Floating Widget is displayed. Test Failed");
        } catch (NoSuchElementException e) {
            System.out.println("PASSED: Floating Widget is not displayed");
        }

    }



    @AfterClass
    public void quit(){
        driver.quit();
        driverFactory.cleanWebDriver();
    }
}

