package execution.reports.previous.customer.upload;

import common.cases.CommonScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import talkable.common.elements.pagination.Pagination;
import util.*;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1ONGHch4nOJ28fGgNG4Hz3THlX1NWn8KxwM74fBSLngM/
 * */
@Listeners(util.Listeners.class)
public class PreviousCustomerUploadTesting_v2 extends BaseTest{

    private String siteUrl;

    @BeforeClass
    public void login(){
        driver.navigate().to(EnvFactory.getEnvUrl());
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(EnvFactory.getReportsUser(), EnvFactory.getPassword());
        siteUrl = DriverConfig.getDriver().getCurrentUrl();
    }

    @BeforeMethod
    public void navigateToAdminUrl(){
        CommonScenarios.navigateToUrl(siteUrl);
        ReportsScenarios.openExistingCustomersReport();
    }

    @Test(dataProvider = "getTestData")
    public void uploadFile(String fileName, String expectedProgress, String expectedUploadedEmails, String expectedStatus){
        ReportsScenarios.uploadPreviousCustomersCsvFile(fileName);
        ReportsScenarios.assertRowInPreviousCustomersReport(expectedProgress, expectedUploadedEmails, expectedStatus);
    }

    @Test(dependsOnMethods = "uploadFile")
    public void searchEmail(){
        String email = ReportsScenarios.getFirstEmailFromPreviousCustomerReport();
        ReportsScenarios.searchEmailInPreviousCustomerReport(email);
        Assert.assertEquals(
                ReportsScenarios.getTotalEmailsCountFromPreviousCustomerReport(),
                "Total: 1",
                "Incorrect Total emails value in the Previous Customer report.");
        Assert.assertEquals(
                ReportsScenarios.getFirstEmailFromPreviousCustomerReport(),
                email,
                "Incorrect email is returned after search in the Previous Customer report.");
    }

    @Test(dependsOnMethods = "uploadFile")
    public void verifyPagination(){
        ReportsScenarios.searchEmailInPreviousCustomerReport("Unexisting email");//search unexisting email to clear all data
        ReportsScenarios.searchEmailInPreviousCustomerReport("");
        Pagination csvListTablePagination = ReportsScenarios.getPaginationForCsvListTableInPreviousCustomer();
        ReportsScenarios.verifyPagination(csvListTablePagination);
        Pagination emailsListTablePagination = ReportsScenarios.getPaginationForCsvListTableInPreviousCustomer();
        ReportsScenarios.verifyPagination(emailsListTablePagination);
    }


    @DataProvider
    private Object[][] getTestData() {
        return new Object[][]{
                {"01_positiveSet.csv", "100%", "30", "Completed"},
                {"02_positiveSetForDuplicatedRecordsTesting.csv", "100%", "1", "Completed"},
                {"03_positiveWithDuplicates.csv", "100%", "3", "Completed"},
                {"04_positiveAndNegative.csv", "100%", "2", "Completed"},
                {"05_negativeWithMultipleTests.csv", "100%", "2", "Completed"},
                {"06_positiveSet2.csv", "100%", "2", "Completed"}
//                {"08_zipTest.csv.zip" ,"100%", "8", "Completed"}
        };

    }


}
