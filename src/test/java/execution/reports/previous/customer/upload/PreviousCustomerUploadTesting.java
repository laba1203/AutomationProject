package execution.reports.previous.customer.upload;

import common.cases.CommonScenarios;
import common.cases.ReportsScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import talkable.common.elements.pagination.Pagination;
import talkable.talkableSite.reports.previousCustomersReport.PreviousCustomersReportPage;
import util.EnvFactory;
import util.TestDataConverter;
import util.TestDataGenerator;

import java.io.IOException;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1ONGHch4nOJ28fGgNG4Hz3THlX1NWn8KxwM74fBSLngM/
 * */
public class PreviousCustomerUploadTesting extends BaseTest{
//    private static final String fileName = "testDataForExistingCustomersReport.csv";

    @Test
    public void test1_login(){
        driver.navigate().to(EnvFactory.getEnvUrl());
        CommonScenarios.login(EnvFactory.getReportsUser(), EnvFactory.getPassword());
    }


    @Test(dependsOnMethods = "test1_login")
    public void test2_createNewSiteAndOpenReportsPage(){
        CommonScenarios
                .createNewSite("ECR_" + TestDataGenerator.getRandomId(), "www.test.com")
                .header.clickReportsButton()
                .openExistingCustomerReport();
    }

    @Test(dataProvider = "getTestData", dependsOnMethods = "test2_createNewSiteAndOpenReportsPage")
    public void test3_uploadFile(String fileName, String expectedProgress, String expectedUploadedEmails, String expectedStatus){
        ReportsScenarios
                .previousCustomerUploadTesting(fileName, expectedProgress, expectedUploadedEmails, expectedStatus);
        driver.navigate().to("https://admin.talkable.com/sites/ecr-563089371/previous_customers");
    }

    @Test(dependsOnMethods = "test3_uploadFile")
    public void searchEmail(){
        PreviousCustomersReportPage report = new PreviousCustomersReportPage();
        String email = report.getFirstEmailValue();
        String total = report
                .searchEmail(email)
                .getTotalValue();
        Assert.assertEquals(total, "Total: 1", "FAILED: Incorrect total value");
        Assert.assertEquals(report.getFirstEmailValue(), email, "FAILED: Incorrect email is returned");
    }

    @Test(dependsOnMethods = "test3_uploadFile")
    public void verifyPagination(){
        Pagination pagination = new PreviousCustomersReportPage()
                .searchEmail("")
                .getUpperPaginationForCsvListsTable();

        CommonScenarios.verifyPagination(pagination);
        CommonScenarios.verifyPagination(new PreviousCustomersReportPage().getUpperPaginationForCustomersList());
    }


    @DataProvider
    private Object[][] getTestData() /*throws IOException*/ {
//        TestDataConverter data = new TestDataConverter(fileName);
//        return data.getDataFromFile();
        return new Object[][]{
                {"01_positiveSet.csv", "100%", "30", "Completed"},
                {"02_positiveSetForDuplicatedRecordsTesting.csv", "100%", "1", "Completed"},
                {"03_positiveWithDuplicates.csv", "100%", "3", "Completed"},
                {"04_positiveAndNegative.csv", "100%", "2", "Completed"},
                {"05_negativeWithMultipleTests.csv", "0%", "0", "Failed"},
                {"08_zipTest.csv.zip" ,"100%", "8", "Completed"}
        };

    }


}
