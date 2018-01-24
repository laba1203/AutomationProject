package talkable.reports.previousCustomersReport;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import talkable.reports.previousCustomersReport.elements.DropZoneInput;
import talkable.reports.previousCustomersReport.elements.TotalCustomersLists;
import talkable.reports.previousCustomersReport.elements.UploadNewCSVButton;
import talkable.reports.previousCustomersReport.elements.ClosePopupButton;
import talkable.reports.previousCustomersReport.uploadedCSVListsTable.UploadedCSVListsTable;
import util.TestArtifactsProvider;

import java.util.ArrayList;

public class PreviousCustomersReportPage extends AbstractElementsContainer {

    private final static String title = "Existing Customers Lists | Talkable";

    private UploadedCSVListsTable uploadedCSVLists;
    private UploadNewCSVButton uploadNewCSVButton;
    private TotalCustomersLists totalCustomersLists;

    private DropZoneInput dropZone;


    public PreviousCustomersReportPage() {
//        isPageOpened(title);

        uploadedCSVLists = new UploadedCSVListsTable();
        uploadNewCSVButton = new UploadNewCSVButton();
        totalCustomersLists = new TotalCustomersLists();
    }

    public void uploadFile(String fileName){
        uploadNewCSVButton.click();
        dropZone = new DropZoneInput();
        ClosePopupButton closePopupButton = new ClosePopupButton();
        dropZone.sendKeys(TestArtifactsProvider.getPreviousCustomerFilePath(fileName));
        wait.until(ExpectedConditions.invisibilityOf(closePopupButton.getWebElement()));
        uploadedCSVLists = new UploadedCSVListsTable();
    }

    /*Returns values of the row in array:
     * 0 - File Name
     * 1 - Progress
     * 2 - Emails Uploaded
     * 3 - Status*/
    public ArrayList getUploadedCSVListsRowValues(int rowNumber){
//        this.refresh();
        uploadedCSVLists = new UploadedCSVListsTable();
        return uploadedCSVLists.getRowValues(rowNumber);
    }


     /*Returns values of the first row in array:
     * 0 - File Name
     * 1 - Progress
     * 2 - Emails Uploaded
     * 3 - Status*/
    public ArrayList<String> getFirstRowValuesFromUploadedCSVLists(){
//        this.refresh();
        uploadedCSVLists = new UploadedCSVListsTable();
        return uploadedCSVLists.getRowValues(1);
    }

    public String getUploadedListsCount(){
        String value = totalCustomersLists.getText();

        if(value.equals("Not found")) {
            return value;
        }
        int end = value.indexOf("D");
        return value.substring(0, end);

    }

    public UploadedCSVListsTable getUploadedCSVListsTable(){
        return uploadedCSVLists;
    }


}
