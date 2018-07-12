package execution.reports.coupon.codes.upload;

import common.cases.CommonScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.DriverConfig;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;


import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType.FixedAmount;
import static talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules.DiscountType.Percentage;


    /*Scenario#1. Upload Coupon code CSV list.
    * 1. Open Coupon Code report.
    * 2. Upload file with coupon codes.
    * 3. Verify count of uploaded coupons.
    * 4. Verify status of the Coupons List.
    *
    * Scenario#2. Upload Coupon code CSV list.
    * 1. Open Coupon Code report.
    * 2. Populate coupon codes manually with coupon codes.
    * 3. Verify count of uploaded coupons.
    * 4. Verify status of the Coupons List.
    *
    * Scenario#3. Add coupons to the existing list.
    * 1.
    *
    * */

    @Listeners(util.Listeners.class)
public class UploadCouponCodesTests extends BaseTest{

    private String user = PropertyLoader.loadProperty("talkable.user.reports");
    private String pswrd = EnvFactory.getPassword();
    private String siteUrl;
    private String manualCouponsListName = "Count List " + TestDataGenerator.getRandomId();
    private String[] manualCouponList = {"test1", "test2", "test3", "test4", "test5", "test6"};


    @BeforeClass
    public void login(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(user, pswrd);
        siteUrl = CommonScenarios.getCurrentUrl();
    }

    @BeforeMethod
    public void testSetup(){
        DriverConfig.getDriver().navigate().to(siteUrl);
    }

    /*Scenario#1*/
    @Test
    public void uploadCouponListViaCSV(){
        //data:
        String couponsListName = "Count List " + TestDataGenerator.getRandomId();
        String expirationDate = "12/01/2030";
        int amount = 5;
        String fileName = "coupon_codes_list.csv";
        String expectedCouponCodesCount = "20";

        ReportsScenarios.openCouponCodesReport();
        ReportsScenarios.createCouponsListViaCsv(couponsListName, expirationDate, amount, FixedAmount, fileName);

        Assert.assertEquals(
                ReportsScenarios.getCouponsTotalCountFromCouponListPage(),
                expectedCouponCodesCount,
                "Incorrect Counpon codes count."
        );

        Assert.assertEquals(
                ReportsScenarios.getCouponsListStatusFromCouponListPage(),
                "Inactive",
                "Incorrect Counpon list status."
        );

    }

    /*Scenario#2*/
//    @Test
    public void uploadCouponListManually(){
        String expirationDate = "12/01/2030";
        int amount = 5;
        String expectedCouponCodesCount = String.valueOf(manualCouponList.length);

        ReportsScenarios.openCouponCodesReport();
        ReportsScenarios.createCouponsListManually(manualCouponsListName, expirationDate, amount, Percentage, manualCouponList);

        Assert.assertEquals(
                ReportsScenarios.getCouponsTotalCountFromCouponListPage(),
                expectedCouponCodesCount,
                "Incorrect Counpon codes count."
        );

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
        String[] newCoupons = {"test21", "test22", "test23"};
        String expectedCouponCodesCount = String.valueOf(manualCouponList.length + newCoupons.length);

        ReportsScenarios.openCouponCodesReport();
        ReportsScenarios.addCouponsToTheListManually(manualCouponsListName, newCoupons);

        Assert.assertEquals(
                ReportsScenarios.getCouponsTotalCountFromCouponListPage(),
                expectedCouponCodesCount,
                "Incorrect Coupon codes count."
        );

        Assert.assertEquals(
                ReportsScenarios.isCouponCodePresentInTheList(newCoupons[0]),
                true,
                "Newly updated coupon code is not available in the Coupon List <" + ReportsScenarios.getNameFromCouponsList() + ">."
        );

    }



}
