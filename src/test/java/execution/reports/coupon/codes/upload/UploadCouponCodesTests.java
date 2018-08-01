package execution.reports.coupon.codes.upload;

import common.cases.CommonScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import util.DriverConfig;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;


import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType.FixedAmount;
import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType.Percentage;


    /*Scenario#1. Upload Coupon code CSV list.
    * 1. Open Coupon Code report.
    * 2. Upload file with coupon codes.
    * 3. Verify count of uploaded coupons.
    * 4. Verify status of the Coupons List.
    *
    * Scenario#2. Upload Coupon codes manually.
    * 1. Open Coupon Code report.
    * 2. Populate coupon codes manually with coupon codes.
    * 3. Verify count of uploaded coupons.
    * 4. Verify status of the Coupons List.
    *
    * Scenario#3. Add coupons to the existing list.
    * 1. Open coupon code from Scenario#2.
    * 2. Upload new coupon codes.
    * 3. Verify new coupon codes count.
    * 4. Verify one new coupon code in the coupon codes list.
    *
    * Scenario#4. Edit Coupon List
    * 1. Open coupon code from Scenario#1
    * 2. Click Edit.
    * 3. Change Name, Amount, Discount type.
    * 4. Save changes.
    * 5. Verify updated values on Coupon List page.
    * */

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1cVBZH9ucZwFKOd8_sqf3vnCg2nr6Y9uV38DPI3IyEKg
 * */
public class UploadCouponCodesTests extends BaseTest{

    private String user = PropertyLoader.loadProperty("talkable.user.reports");
    private String pswrd = EnvFactory.getPassword();
    private String siteUrl;
    // test data:
    private String manualCouponsListName = "Manual Count List " + TestDataGenerator.getRandomId();
    private String uploadedCouponsListName = "Uploaded Count List " + TestDataGenerator.getRandomId();
    private String[] manualCouponList = {"TEST1", "TEST2", "TEST3", "TEST4", "TEST5", "TEST6"};
    private PageCampaignRules.DiscountType discountType = FixedAmount;


    @BeforeClass
    public void login(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(user, pswrd);
        siteUrl = CommonScenarios.getCurrentUrl();
    }

    @BeforeMethod
    public void testSetup(){
        Log.debagRecord("Before method starts execution in <" + getClass().getName() + ">.");
        DriverConfig.getDriver().navigate().to(siteUrl);
    }

    /*Scenario#1*/
    @Test
    public void uploadCouponListViaCSV(){
        //data:
//        String couponsListName = "Count List " + TestDataGenerator.getRandomId();
        String expirationDate = "12/01/2030";
        int amount = 5;
        String fileName = "coupon_codes_list.csv";
        String expectedCouponCodesCount = "20";

        ReportsScenarios.openCouponCodesReport();
        ReportsScenarios.createCouponsListViaCsv(uploadedCouponsListName, expirationDate, amount, discountType, fileName);
        ReportsScenarios.assertTotalCouponCountOnCouponListPage(expectedCouponCodesCount);

        Assert.assertEquals(
                ReportsScenarios.getCouponsListStatusFromCouponListPage(),
                "Inactive",
                "Incorrect Counpon list status."
        );
    }

    /*Scenario#2*/
    @Test
    public void uploadCouponListManually(){
        String expirationDate = "12/01/2030";
        int amount = 5;
        String expectedCouponCodesCount = String.valueOf(manualCouponList.length);

        ReportsScenarios.openCouponCodesReport();
        ReportsScenarios.createCouponsListManually(manualCouponsListName, expirationDate, amount, Percentage, manualCouponList);
        ReportsScenarios.assertTotalCouponCountOnCouponListPage(expectedCouponCodesCount);

        Assert.assertEquals(
                ReportsScenarios.getCouponsListStatusFromCouponListPage(),
                "Inactive",
                "Incorrect Counpon list status."
        );
    }

    /*Scenario#3*/
    @Test(dependsOnMethods = "uploadCouponListManually")
    public void addCouponCodesToExistingList(){
        //data
        String[] newCoupons = {"TEST21", "TEST22", "TEST23"};
        String expectedCouponCodesCount = String.valueOf(manualCouponList.length + newCoupons.length);

        ReportsScenarios.openCouponCodesReport();
        ReportsScenarios.addCouponsToTheListManually(manualCouponsListName, newCoupons);
        ReportsScenarios.assertTotalCouponCountOnCouponListPage(expectedCouponCodesCount);

        String uploadedCoupon = newCoupons[0];
        Assert.assertEquals(
                ReportsScenarios.isCouponCodePresentInTheList(uploadedCoupon),
                true,
                "Newly updated coupon code <" + uploadedCoupon + "> is not available in the Coupon List <" + ReportsScenarios.getNameFromCouponsList() + ">."
        );
    }

    /*Scenario#4. Edit Coupon List*/
    @Test(dependsOnMethods = {"uploadCouponListViaCSV"})
    public void editCouponList(){
        String newCouponListName = "Updated Coupon List_" + TestDataGenerator.getRandomId();
        int newAmount = 23;

        ReportsScenarios.openCouponCodesReport();
        ReportsScenarios.openCouponList(uploadedCouponsListName);
        ReportsScenarios.editCouponList(newCouponListName, newAmount, Percentage);

        ReportsScenarios.assertCouponsListValues(newCouponListName, newAmount, Percentage);
    }

}
