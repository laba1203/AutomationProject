package common.cases;

import org.testng.Assert;
import talkable.talkableSite.headerFrame.Header;
import talkable.talkableSite.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.talkableSite.reports.referralsReport.PageReferralsReport;

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
    public static void previousCustomerUploadTesting(String fileName, String expectedProgress,String expectedUploadedEmails, String expectedStatus){
        PreviousCustomersReportPage previousCustomersReport = new PreviousCustomersReportPage();
        previousCustomersReport.uploadFile(fileName);
        previousCustomersReport.waitTillFileProcessed();

        String actualFileName = previousCustomersReport.getRowWithCsv(1).getFileName();
        String actualProgress = previousCustomersReport.getRowWithCsv(1).getProgress();
        String actualUploadedEmails = previousCustomersReport.getRowWithCsv(1).getEmailsUploaded();
        String actualStatus = previousCustomersReport.getRowWithCsv(1).getStatus();

        Assert.assertEquals(actualFileName, fileName, "FAILED: Incorrect FileName");
        Assert.assertEquals(actualProgress, expectedProgress, "FAILED: Incorrect Progress");
        Assert.assertEquals(actualUploadedEmails, expectedUploadedEmails, "FAILED: Incorrect UploadedEmails");
        Assert.assertEquals(actualStatus, expectedStatus, "FAILED: Incorrect Status");

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



    /* End of scenarios for Referral report*/
}
