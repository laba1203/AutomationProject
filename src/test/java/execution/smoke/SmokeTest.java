

package execution.smoke;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import common.cases.CommonScenarios;
import customerSite.talkableFrame.floatingWidgete.advocateTrigerWidget.AdvocateTriggerWidgetFrame;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.campaign.pages.campaignDetailsPage.CampaignDetailsPage;
import talkable.campaign.pages.campaignNavigationMenu.CampaignNavigationMenu;
import talkable.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.campaign.pages.campaignRulesPage.PageCampaignRules.CouponCodeType;
import talkable.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType;
import talkable.campaign.pages.campaignRulesPage.PageCampaignRules.IncentiveType;
import talkable.campaign.pages.createNewPurchasePage.CreateNewPurchasePage;
import talkable.campaignsPage.PageCampaigns;
import talkable.createNewCampaignPage.CreateNewCampaignPage.CampaignType;
import talkable.createNewCampaignPage.CreateNewCampaignPage.PlacementType;
import talkable.headerFrame.Header;
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
        CampaignDetailsPage detailsPage = CommonScenarios.initiateCampaignCreation(CampaignType.Invite, PlacementType.FloatingWidget);
        Assert.assertEquals(detailsPage.campaignNavigationMenu.getCampaignStatus(), "Status: Test", "FAILED: Incorrect campaign status");
    }

// 4. Navigate to Rules page
// 5. Modify Campaign name
    @Test
    public void test04_setCampaignNameOnRulesPage() {
        String name = campaignName;
        PageCampaignRules rulesPage = (new CampaignDetailsPage()).campaignNavigationMenu.openRulesPage();
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

        Assert.assertEquals(advocateOfferDeadlineDate, rulesPage.getAdvocateDeadlineDate(), "FAILED: Incorrect Advocate Deadline Date");
        Assert.assertEquals(endTime, rulesPage.getAdvocateDeadlineTime(), "FAILED: Incorrect Advocate Deadline Time");

        Assert.assertEquals(friendDeadline, rulesPage.getFriendDeadlineDate(), "FAILED: Incorrect Friend Deadline Date");
        Assert.assertEquals(endTime, rulesPage.getFriendDeadlineTime(), "FAILED: Incorrect Friend Deadline Time");
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
    @Test
    public void test09_createTestOffer() {
        CampaignDetailsPage detailsPage = new CampaignDetailsPage();
        CreateNewPurchasePage newPurchasePage = detailsPage.clickCreateTestOffer();
        detailsPage = newPurchasePage.createOfferAndSwitchToCampaign();
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
        } catch (ElementNotFoundException var2) {
            System.out.println("PASSED: Floating Widget is not displayed");
        }

    }
}

