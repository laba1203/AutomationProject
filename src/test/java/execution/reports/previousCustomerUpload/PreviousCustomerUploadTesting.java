package execution.reports.previousCustomerUpload;

import common.cases.ReportsScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import talkable.talkableSite.headerFrame.Header;
import talkable.talkableSite.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.talkableSite.reports.reportsPage.ReportsPage;
import util.DriverConfig;
import util.EnvFactory;
import util.TestDataConverter;

import java.io.IOException;

/*Link to test scenario: https://docs.google.com/spreadsheets/d/1ONGHch4nOJ28fGgNG4Hz3THlX1NWn8KxwM74fBSLngM/
 * */
public class PreviousCustomerUploadTesting extends ReportsScenarios{

    private static final String fileName = "testDataForExistingCustomersReport.csv";

    private WebDriver driver = new DriverConfig().getDriver();


    @BeforeClass
    public void setup(){
        driver.navigate().to(EnvFactory.getEnvUrl()); //"https://void.talkable.com"
        Header header = login(EnvFactory.getUser(), EnvFactory.getPassword());
        //Navigate to Existing Customers Report
        ReportsPage reportsPage = header.clickReportsButton();
        reportsPage.openExistingCustomerReport();
    }

    @Test(dataProvider = "getTestData")
    public void testing(String fileName, String expectedProgress, String expectedUploadedEmails, String expectedStatus){
        previousCustomerUploadTesting(fileName, expectedProgress, expectedUploadedEmails, expectedStatus);
    }

    @AfterClass
    public void quit(){
        driver.quit();
        new DriverConfig().cleanWebDriver();

    }


    @DataProvider
    private Object[][] getTestData() throws IOException {
        TestDataConverter data = new TestDataConverter(fileName);
        return data.getDataFromFile();
    }
}
