package common.cases.functionalities;

import common.cases.CommonScenarios;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import talkable.common.elements.pagination.Pagination;
import talkable.talkableSite.campaign.pages.campaignRulesPage.PageCampaignRules;
import talkable.talkableSite.headerFrame.Header;
import talkable.talkableSite.reports.CountableReport;
import talkable.talkableSite.reports.FilterableReport;
import talkable.talkableSite.reports.ReportsPage;
import talkable.talkableSite.reports.couponLists.CouponListPage;
import talkable.talkableSite.reports.couponLists.CouponListsReportPage;
import talkable.talkableSite.reports.peopleReport.PeopleReportPage;
import talkable.talkableSite.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.talkableSite.reports.purchases.report.PurchasesReportPage;
import talkable.talkableSite.reports.referrals.PageReferralsReport;
import talkable.talkableSite.reports.rewards.RewardsReportPage;
import talkable.talkableSite.reports.settingsChanges.SettingsChangesReportPage;
import talkable.talkableSite.reports.staticAssets.StaticAssetsReportPage;
import util.DriverConfig;
import util.Util;
import util.logging.Log;

public class ReportsScenarios extends CommonScenarios {

    public static ReportsPage openReportsPage(){
        return new Header()
                .clickReportsButton();
    }

    public static void assertRowsCountFromReport(String expectedCount, CountableReport report){
        Assert.assertEquals(
                report.getTotalCount(),
                expectedCount,
                "Incorrect Total count in the <" + report.getClass().getName() + "> report."
        );
    }

    public static void filterReportByDate(FilterableReport report, String startDate, String endDate){
        report
                .enterFilterDates(startDate, endDate)
                .generate();
        try {
            report.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Log.logRecord("Report <" + report.getClass().getName() + "> was filtered.");
    }

    /* Scenarios for Previous Customers report */

    public static void openExistingCustomersReport(){
        new Header()
                .clickReportsButton()
                .openExistingCustomerReport();
    }

    /*Scenario to test uploading of Previous Customers list
    * Precondition:
    * 1. User should have STAFF ONLY permission
    * 2. User should be logged in and Existing Customer Report page should be opened
    * @fileName - name of file with emails, located in src/main/resources/testData/previousCustomersList
    * @expectedUploadedEmails - count of emails which are expected to be uploaded in the file
    * @expectedStatus - expected status of the processing
    * */
    public static void previousCustomerUploadTesting(String fileName, String expectedProgress,String expectedUploadedEmails, String expectedStatus){
        PreviousCustomersReportPage previousCustomersReport = new PreviousCustomersReportPage();
        previousCustomersReport.uploadFile(fileName);
        previousCustomersReport.waitTillFileProcessed();

        String actualFileName = previousCustomersReport.getFirstRowWithCsv().getFileName();
        String actualProgress = previousCustomersReport.getFirstRowWithCsv().getProgress();
        String actualUploadedEmails = previousCustomersReport.getFirstRowWithCsv().getEmailsUploaded();
        String actualStatus = previousCustomersReport.getFirstRowWithCsv().getStatus();

        Assert.assertEquals(actualFileName, fileName, "FAILED: Incorrect FileName");
        Assert.assertEquals(actualProgress, expectedProgress, "FAILED: Incorrect Progress");
        Assert.assertEquals(actualUploadedEmails, expectedUploadedEmails, "FAILED: Incorrect UploadedEmails");
        Assert.assertEquals(actualStatus, expectedStatus, "FAILED: Incorrect Status");
    }

    public static void uploadPreviousCustomersCsvFile(String fileName){
        PreviousCustomersReportPage previousCustomersReport = new PreviousCustomersReportPage();
        previousCustomersReport.uploadFile(fileName);
        previousCustomersReport.waitTillFileProcessed();
        String actualFileName = new PreviousCustomersReportPage().getFirstRowWithCsv().getFileName();
        Assert.assertEquals(actualFileName, fileName, "FAILED: Incorrect FileName");
        Log.logRecord("Previous Customers file <" + fileName + "> was successfully uploaded.");
    }

    public static void assertRowInPreviousCustomersReport(String expectedProgress, String expectedUploadedEmails, String expectedStatus){
        String actualProgress = new PreviousCustomersReportPage().getFirstRowWithCsv().getProgress();
        String actualUploadedEmails = new PreviousCustomersReportPage().getFirstRowWithCsv().getEmailsUploaded();
        String actualStatus = new PreviousCustomersReportPage().getFirstRowWithCsv().getStatus();

        Assert.assertEquals(actualProgress, expectedProgress, "FAILED: Incorrect Progress for file <"+new PreviousCustomersReportPage().getFirstRowWithCsv().getFileName()+">.");
        Assert.assertEquals(actualUploadedEmails, expectedUploadedEmails, "FAILED: Incorrect UploadedEmails for file <" +new PreviousCustomersReportPage().getFirstRowWithCsv().getFileName()+">.");
        Assert.assertEquals(actualStatus, expectedStatus, "FAILED: Incorrect Status for file <"+new PreviousCustomersReportPage().getFirstRowWithCsv().getFileName()+">");
    }

    public static String getFirstEmailFromPreviousCustomerReport(){
        return new PreviousCustomersReportPage().getFirstEmailValue();
    }

    public static void searchEmailInPreviousCustomerReport(String email){
        new PreviousCustomersReportPage().searchEmail(email);
    }

    public static String getTotalEmailsCountFromPreviousCustomerReport(){
        return new PreviousCustomersReportPage().getTotalValue();
    }

    public static Pagination getPaginationForCsvListTableInPreviousCustomer(){
        return new PreviousCustomersReportPage()
                .getUpperPaginationForCsvListsTable();
    }

    public static Pagination getPaginationForEmailsListTableInPreviousCustomer(){
        return new PreviousCustomersReportPage()
                .getUpperPaginationForCustomersList();
    }

    /* End of scenarios for Previous Customers Upload */


    /*Scenarios for Referrals report
    * */
    public static PageReferralsReport openReferralsReport(){
        return new Header().clickReportsButton().openReferralsReport();
    }

    public static String getAdvocateEmailFromReferralReportFirstRow(){
        return new PageReferralsReport().getFirstReferralRow().getAdvocateEmail();
    }

    public static PageReferralsReport approveFirstRowInReferralsReport(){
        return new PageReferralsReport().getFirstReferralRow().approve();
    }

    public static PageReferralsReport voidFirstRowInReferralsReport(){
        return new PageReferralsReport().getFirstReferralRow().voidReferral();
    }

    public static String getFirstRowStatusFromReferralReport(){
        return new PageReferralsReport().getFirstReferralRow().getRowStatus();
    }

    public static String getFriendUnpaidReasonFromTheFirstRow(){
        try {
            return new PageReferralsReport().getFirstReferralRow().getFriendRewardUnpaidReason();
        }catch (NotFoundException | AssertionError e){
            String friendEmail = new PageReferralsReport().getFirstReferralRow().getFriendEmail();
            Assert.fail("FAILED: Reward Unpaid Reason is not available for the Friend (Friend email = <"+friendEmail+">). Possibly the reward was given to the Friend.");
            return null;
        }
    }

    public static String getAdvocateUnpaidReasonFromTheFirstRow(){
        try {
            return new PageReferralsReport().getFirstReferralRow().getAdvocateRewardUnpaidReason();
        }catch (NotFoundException | AssertionError e){
            String advocateEmail = new PageReferralsReport().getFirstReferralRow().getAdvocateEmail();
            Assert.fail("FAILED: Reward Unpaid Reason is not available for the Advocate (email = <"+advocateEmail+">). Possibly the reward was given to the Advocate.");
            return null;
        }
    }

    public static void assertThatReferralCreatedForTheAdvocate(String advocateEmail){
        Assert.assertEquals(
                ReportsScenarios.getAdvocateEmailFromReferralReportFirstRow(),
                advocateEmail,
                "FAILED: Referral is not created for advocate: <" + advocateEmail + ">"
        );
        Log.logRecord("Referral created for advocate <" + advocateEmail + ">");
    }

    public static String getReferralsReportTotal(){
        return new PageReferralsReport().getTotalRowsCount();
    }



    /* End of scenarios for Referral report*/

    /*  --- Scenairios for Rewards Report ---   */

    public static void openRewardsReport(){
        new Header().clickReportsButton().openRewardsReport();
        Log.logRecord("Rewards Report is opened.");
    }

    public static String getRewardsReportTotal(){
        return new RewardsReportPage().getTotalRowsCount();
    }



    /* End of scenarios for Rewards report*/

    /*  --- Scenairios for Coupon Codes Report ---   */

    public static void openCouponCodesReport(){
        new Header().clickReportsButton().openCouponsListReport();
    }

    public static void createCouponsListViaCsv(
            String couponsListName,
            String expirationDate,
            int amount,
            PageCampaignRules.DiscountType discount,
            String fileName){
        new CouponListsReportPage()
                .clickCreateNewList()
                .populateFields(couponsListName, expirationDate, amount)
                .selectDiscountType(discount)
                .uploadCcvFile(fileName)
                .saveChanges();
    }


    public static void createCouponsListManually(
            String couponsListName,
            String expirationDate,
            int amount,
            PageCampaignRules.DiscountType discount,
            String[] couponList)
    {
        String coupons = Util.stringArrayToString(couponList);
        new CouponListsReportPage()
                .clickCreateNewList()
                .populateFields(couponsListName, expirationDate, amount)
                .selectDiscountType(discount)
                .populateCouponCodesManually(coupons)
                .saveChanges();
    }

    public static void addCouponsToTheListManually(String couponListName, String[] couponList){
        String coupons = Util.stringArrayToString(couponList);
        openCouponList(couponListName)
                .addCouponsManually(coupons);
    }

    public static CouponListPage openCouponList(String couponListName){
        return new CouponListsReportPage()
                .openCouponList(couponListName);
    }

    public static void editCouponList(String newCouponListName, int newAmount, PageCampaignRules.DiscountType discountType){
        new CouponListPage()
                .edit()
                .populateFields(newCouponListName, newAmount)
                .selectDiscountType(discountType)
                .saveChanges();
    }

    public static void assertCouponsListValues(String expectedName, int expectedAmount, PageCampaignRules.DiscountType expectedDiscount){
        Assert.assertEquals(
                new CouponListPage().getListName(),
                expectedName,
                "Incorrect Coupon List Name on the Coupon List page.");
        Assert.assertEquals(
                new CouponListPage().getAmount(),
                String.valueOf(expectedAmount),
                "Incorrect Coupon List amount on the Coupon List page.");
        Assert.assertEquals(
                new CouponListPage().getDiscountType(),
                expectedDiscount,
                "Incorrect Coupon List discount type on the Coupon List page.");
    }

    public static void assertTotalCouponCountOnCouponListPage(String expectedCouponsCount){
        try {
            assertCouponCodes(expectedCouponsCount);
            Log.logRecord("Coupon codes Total count is not refreshed on the Coupon List page.");
        }
        catch (AssertionError e){
            Log.logRecord("Coupon codes Total count is not refreshed on the Coupon List page.");
            Log.logRecord("Refreshing the page...");
            DriverConfig.getDriver().navigate().refresh();
            assertCouponCodes(expectedCouponsCount);
        }
    }

    private static void assertCouponCodes(String expectedCouponsCount){
        Assert.assertEquals(
                new CouponListPage().getCouponsTotalCount(),
                expectedCouponsCount,
                "Incorrect Coupon codes count on Coupon List page for <" + new CouponListPage().getListName() + ">."
        );
    }

    public static String getCouponsListStatusFromCouponListPage(){
        return new CouponListPage().getStatus();
    }

    public static boolean isCouponCodePresentInTheList(String couponCode){
        return new CouponListPage().isCouponPreset(couponCode);
    }

    public static String getNameFromCouponsList(){
        return new CouponListPage().getListName();
    }
    /* End of scenarios for Coupon Codes Report*/


    /* Access Management scenarios */
    public static void assertAccessAbsenceToReport(String reportName){
        new Header()
                .clickReportsButton()
                .openReportWithPiiDataUnderNonPiiAccess(reportName);
    }

    public static void assertAvailableAccessToReport(String reportName){
        ReportsPage reportsPage = new Header().clickReportsButton();
        switch (reportName){
            default:
                Assert.fail("Unknown report name <" + reportName + "> was passed to assertAvailableAccessToReport()");
                break;
            case "Static Assets":
                reportsPage.openStaticAssetsReport();
                break;
            case "CouponLists":
                reportsPage.openCouponsListReport();
                break;
            case "Locale entries":
                reportsPage.openLocaleEntriesReport();
                break;
            case "Settings Changes":
                reportsPage.openSettingsChangesReport();
                break;
            case "Performance by Channel":
                reportsPage.openPerformanceByChannelReport();
                break;
            case "Weekly Stats":
                reportsPage.openWeeklyStatsReport();
                break;
            case "Summary Report":
                reportsPage.openSummaryReport();
                break;
            case "Email Performance":
                reportsPage.openEmailPerformanceReport();
                break;
            case "Performance Over Time":
                reportsPage.openPerformanceOverTimeReport();
                break;
            case "Metrics Aggregation":
                reportsPage.openMetricsAggregationReport();
                break;
            case "A/B Tests":
                reportsPage.openAbTestsReport();
                break;
            case "Referrals Over Time":
                reportsPage.openReferralsOverTimeReport();
                break;
            case "Email Conversion":
                reportsPage.openEmailConversionReport();
                break;
            case "Page Snapshots":
                reportsPage.openPageSnapshotsReport();
                break;
        }
    }


    /* End of Access Management scenarios */

    /*  StaticAssetsReport scenarios */
    public static StaticAssetsReportPage openStaticAssetsReport(){
        return openReportsPage()
                .openStaticAssetsReport();
    }

    public static void filterByNameInStaticAssets(String name){
        new StaticAssetsReportPage()
                .filterByName(name);
    }

    public static void deleteAllRowsFromStaticAssetsReport(){
        new StaticAssetsReportPage()
                .deleteAllRows();
    }
    /* End  StaticAssetsReport scenarios */
    /* Settings Changes report scenarios */
    public static void openSettingsChangesReport(){
        openReportsPage()
                .openSettingsChangesReport();
    }

    public static void assertFirstRowInSettingsChangesReport(String updatedField, String oldValue, String newValue){
        Assert.assertEquals(
                new SettingsChangesReportPage().getUpdatedFieldFromFirstRow(),
                updatedField,
                "Incorrect updated field in the first row of Settings Changes report."
        );
        Assert.assertEquals(
                new SettingsChangesReportPage().getOldValueFromFirstRow(),
                oldValue,
                "Incorrect Old value in the first row of Settings Changes report."
        );
        Assert.assertEquals(
                new SettingsChangesReportPage().getNewValueFromFirstRow(),
                newValue,
                "Incorrect New Value in the first row of Settings Changes report."
        );
        Log.logRecord("Successfully verified first row in Settings Changes report.");
    }
    /* End of Settings Changes report scenarios */

    /* People report scenarios */
    public static void openPeopleReport(){
        openReportsPage()
                .openPeopleReport();
    }

    public static void searchPersonInPeopleReport(String email){
        new PeopleReportPage()
                .search(email);
    }

    public static String getEmailFromFirstRowInPeopleReport(){
        return new PeopleReportPage().getEmailFromFirstRow();
    }
    /* End of People report scenarios */

    /* Purchases report scenarios */
    public static void openPurchasesReport(){
        openReportsPage().openPurchasesReport();
    }

    public static void searchPurchaseInPurchasesReport(String email){
        new PurchasesReportPage().searchByEmail(email);
    }

    public static String getEmailFromFirstRowInPurchasesReport(){
        return new PurchasesReportPage().getEmailFromFirstRow();
    }

    /* End of Purchases report scenarios */
}
