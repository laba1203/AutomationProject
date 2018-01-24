package common.cases;

import org.testng.Assert;
import talkable.reports.previousCustomersReport.PreviousCustomersReportPage;

import java.util.ArrayList;

public class ReportsScenarios extends CommonScenarios{

    /*Scenario to test uploading of Previous Customers list
    * Precondition:
    * 1. User should have STAFF ONLY permission
    * 2. User should be logged in and Existing Customer Report page should be opened
    * @fileName - name of file with emails, located in src/main/resources/testData/previousCustomersList
    * @expectedUploadedEmails - count of emails which are expected to be uploaded in the file
    * @expectedStatus - expected status of the processing
    * */
    public void previousCustomerUploadTesting(String fileName, String expectedProgress,String expectedUploadedEmails, String expectedStatus){
        PreviousCustomersReportPage previousCustomersReport = new PreviousCustomersReportPage();
        previousCustomersReport.uploadFile(fileName);
        ArrayList<String> actualRowValues = previousCustomersReport.getFirstRowValuesFromUploadedCSVLists();
        //compare values:
        String actualFileName = actualRowValues.get(0);
        String actualProgress = actualRowValues.get(1);
        String actualUploadedEmails = actualRowValues.get(2);
        String actualStatus = actualRowValues.get(3);

        Assert.assertEquals(actualFileName, fileName);
        Assert.assertEquals(actualProgress, expectedProgress);
        Assert.assertEquals(actualUploadedEmails, expectedUploadedEmails);
        Assert.assertEquals(actualStatus, expectedStatus);

    }
}
