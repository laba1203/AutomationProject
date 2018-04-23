package execution.reports.previous.customer.upload;

import common.cases.CommonScenarios;
import common.cases.ReportsScenarios;
import execution.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import talkable.talkableSite.headerFrame.Header;
import talkable.talkableSite.reports.reportsPage.ReportsPage;
import util.EnvFactory;
import util.TestDataConverter;
import util.TestDataGenerator;

import java.io.IOException;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1ONGHch4nOJ28fGgNG4Hz3THlX1NWn8KxwM74fBSLngM/
 * */
public class PreviousCustomerUploadTesting extends BaseTest{

    private static final String fileName = "testDataForExistingCustomersReport.csv";

//    @BeforeClass
//    public void setup(){
//        driver.navigate().to(EnvFactory.getEnvUrl()); //"https://void.talkable.com"
////        Header header = CommonScenarios.login(EnvFactory.getReportsUser(), EnvFactory.getPassword());
//        //Navigate to Existing Customers Report
////        ReportsPage reportsPage = header.clickReportsButton();
////        reportsPage.openExistingCustomerReport();
//    }


    @Test
    public void test1_login(){
        driver.navigate().to(EnvFactory.getEnvUrl());
        Header header = CommonScenarios.login(EnvFactory.getReportsUser(), EnvFactory.getPassword());
    }

    @Test(dependsOnMethods = "test1_login")
    public void test2_createNewSiteAndOpenReportsPage(){
        CommonScenarios
                .createNewSite("ECR_" + TestDataGenerator.getRandomId(), "www.test.com")
                .header.clickReportsButton()
                .openExistingCustomerReport();
    }


    @Test(dataProvider = "getTestData", dependsOnMethods = "test2_createNewSiteAndOpenReportsPage")
    public void uploadFile_3(String fileName, String expectedProgress, String expectedUploadedEmails, String expectedStatus){
        ReportsScenarios
                .previousCustomerUploadTesting(fileName, expectedProgress, expectedUploadedEmails, expectedStatus);
    }


    @DataProvider
    private Object[][] getTestData() throws IOException {
        TestDataConverter data = new TestDataConverter(fileName);
        return data.getDataFromFile();
    }
}
