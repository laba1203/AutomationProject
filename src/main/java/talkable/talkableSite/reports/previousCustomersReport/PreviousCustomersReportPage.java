package talkable.talkableSite.reports.previousCustomersReport;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.talkableSite.AbstractTalkableSitePage;
import util.TestArtifactsProvider;
import util.logging.Log;

public class PreviousCustomersReportPage extends AbstractTalkableSitePage {

    private final static String title = "Existing Customers Lists | Talkable";

//    private UploadedCSVListsTable uploadedCSVLists;
    private ElmntUploadNewCSVButton elmntUploadNewCSVButton = new ElmntUploadNewCSVButton();
    private ElmntTotalCustomersLists elmntTotalCustomersLists = new ElmntTotalCustomersLists();
    private SectionUploadedCsvList uploadedCsvList;
    private ElmntDropZoneInput dropZone;


    public PreviousCustomersReportPage() {
//        elmntUploadNewCSVButton = new ElmntUploadNewCSVButton();
//        elmntTotalCustomersLists = new ElmntTotalCustomersLists();
    }

    public void uploadFile(String fileName){
        elmntUploadNewCSVButton.click();
        dropZone = new ElmntDropZoneInput();
        ElmntClosePopupButton elmntClosePopupButton = new ElmntClosePopupButton();
        dropZone.sendKeys(TestArtifactsProvider.getPreviousCustomerFilePath(fileName));
        wait.until(ExpectedConditions.invisibilityOf(elmntClosePopupButton.getWebElement()));
        uploadedCsvList = new SectionUploadedCsvList();
    }


    public void waitTillFileProcessed(){
        int waiter = 0;
        while(!isFileProcessed()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.navigate().refresh();
            waiter++;
            if(waiter==40){
                Assert.fail(Log.fileIsNotProcessedMsg());
                break;
            }
        }
    }


    private boolean isFileProcessed(){
        String progress = new SectionUploadedCsvList().getUploadedFileRow(1).getStatus();
        return !(progress.equals("Pending") || progress.equals("In progress"));
    }


    public SectionUploadedCsvList.Row getRowWithCsv(int rowNumber){
        return new SectionUploadedCsvList().getUploadedFileRow(1);
    }


    public String getUploadedListsCount(){
        String value = elmntTotalCustomersLists.getText();

        if(value.equals("Not found")) {
            return value;
        }
        int end = value.indexOf("D");
        return value.substring(0, end);

    }


}
