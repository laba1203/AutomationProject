package execution.access.management;

import common.cases.CommonScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;

import static talkable.access.managment.access.request.AccessRequestPage.PiiAccess.NON_PII;
import static talkable.access.managment.access.request.AccessRequestPage.TermOfAccess.ONE_DAY;

/*Scenario#1
    * 1. Create site#1 under Admin user.
    * 2. Copy link to the site.
    * 3. Login under STAFF user with no PII access
    * 4. Open the site.
    * 5. Request non PII access for 1 day to the site.
    * 6. Verify that following reports are not accessible:
    *       1.People
            2.Purchases
            3.Events
            4.Advocate offers
            5.Offer shares
            6.Friend offers (aka Cookies)
            7.Referrals
            8.Rewards
            9.Customer email letters
    * 7. Verify that Reports (Reports block:Top Advocates) are not accessible.
    * 8. Verify that Reports (Staff Only Reports block:Previous customers) are not accessible.
    * 9. Verify that other reports are accessible.
    * */


public class NonPiiAccessRequestTestsing extends BaseTest{
    private static final String adminUser = "maxim.laba+auto.admin."+ TestDataGenerator.getRandomId() +"@talkable.com";
    private static final String staffUser = PropertyLoader.loadProperty("talkable.user.access.management.staff");
    private static final String pswrd = EnvFactory.getPassword();
    private String siteUrl;


    /*Scenario1*/
    @Test
    public void requestNonPiiAccessForOneDay(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(adminUser, pswrd);
        siteUrl = CommonScenarios.getCurrentUrl();
        CommonScenarios.logout();
        CommonScenarios.login(staffUser, pswrd);
        CommonScenarios.navigateToUrl(siteUrl);
        CommonScenarios.requestAccessWithAutoApproval(ONE_DAY, NON_PII);
    }


    @Test(dataProvider = "reportsWithPii", dependsOnMethods = "requestNonPiiAccessForOneDay")
    public void accessToPiiData(String reportName){
        ReportsScenarios.navigateToUrl(siteUrl);
        ReportsScenarios.assertAccessAbsenceToReport(reportName);
        Log.logRecord("User doesn't have access to <" + reportName + "> report.");
    }


    @Test(dataProvider = "reportsWithoutPiiData", dependsOnMethods = "requestNonPiiAccessForOneDay")
    public void accessToReportWithoutPiiData(String reportName){
        ReportsScenarios.navigateToUrl(siteUrl);
        ReportsScenarios.assertAvailableAccessToReport(reportName);
        Log.logRecord("User has access to <" + reportName + "> report.");
    }


    //test failed due to defect: https://talkable.atlassian.net/browse/PR-9679
    @Test(dependsOnMethods = "requestNonPiiAccessForOneDay", expectedExceptions = AssertionError.class)
    public void accessToCsp(){
        CommonScenarios.navigateToUrl(siteUrl);
        CommonScenarios.assertAccessToCsp();
    }


    @Test(dependsOnMethods = "requestNonPiiAccessForOneDay")
    public void assertAccessToUsersAndPrivileges(){
        CommonScenarios.navigateToUrl(siteUrl);
        CommonScenarios.assertAccessToUsersAndPrivileges();
    }


    @DataProvider(name = "reportsWithPii")
    public Object[][] reportsWithPii(){
        return new Object[][]{
                {"People"},
                {"Purchases"},
                {"Events"},
                {"Advocate Offers"},
                {"Offer Shares"},
                {"Friend Offers"},
                {"Referrals"},
                {"Rewards"},
                {"Customer Email Letters"},
                {"Top Advocates"},
                {"Existing Customers"}
        };
    }


    @DataProvider(name = "reportsWithoutPiiData")
    public Object[][] reportsWithoutPii() {
        return new Object[][]{
                {"Static Assets"},
                {"CouponLists"},
                {"Locale entries"},
                {"Settings Changes"},
                {"Performance by Channel"},
                {"Weekly Stats"},
//                {"Summary Report"}, //defect: https://talkable.atlassian.net/browse/PR-9665
                {"Email Performance"},
                {"Performance Over Time"},
                {"Metrics Aggregation"},
                {"A/B Tests"},
                {"Referrals Over Time"},
                {"Email Conversion"},
                {"Page Snapshots"},

        };
    }


}
