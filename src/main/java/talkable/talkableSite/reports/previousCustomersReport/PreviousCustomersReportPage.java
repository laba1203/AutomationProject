package talkable.talkableSite.reports.previousCustomersReport;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.common.elements.pagination.Pagination;
import talkable.talkableSite.AbstractTalkableSitePage;
import util.TestArtifactsProvider;
import util.logging.Log;

public class PreviousCustomersReportPage extends AbstractTalkableSitePage {


    private final static By bottomCSVListsPaginationLctr = By.xpath("");

//    private UploadedCSVListsTable uploadedCSVLists;
    private ElmntUploadNewCSVButton elmntUploadNewCSVButton = new ElmntUploadNewCSVButton();
    private ElmntTotalCustomersLists elmntTotalCustomersLists = new ElmntTotalCustomersLists();
    private ExistingCustomersListSection emailList = new ExistingCustomersListSection();
    private SectionUploadedCsvList uploadedCsvList;
    private ElmntDropZoneInput dropZone;




    public PreviousCustomersReportPage() {
    }

    public PreviousCustomersReportPage searchEmail(String text){
        return emailList.filter(text);
    }

    public String getTotalValue(){
        return emailList.getTotal();
    }

    public String getFirstEmailValue(){
        return emailList.getFirstRowValue();
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
        String progress = new SectionUploadedCsvList().getFirstRow().getStatus();
        return !(progress.equals("Pending") || progress.equals("In progress"));
    }

    public Pagination getButtonPaginationForCsvListsTable(){
        return new SectionUploadedCsvList().getBottomPagination();
    }

    public Pagination getUpperPaginationForCsvListsTable(){
        return new SectionUploadedCsvList().getTopPagination();
    }

    public Pagination getButtonPaginationForCustomersList(){
        return new ExistingCustomersListSection().getBottomPagination();
    }

    public Pagination getUpperPaginationForCustomersList(){
        return new ExistingCustomersListSection().getTopPagination();
    }



    public SectionUploadedCsvList.Row getRowWithCsv(int rowNumber){
        return new SectionUploadedCsvList().getFirstRow();
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
