package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.talkableSite.campaignsPage.Table;

import java.util.ArrayList;
import java.util.List;

public class UploadedFilesTable extends AbstractElementsContainer{

    private Element sectionElement = new Element(By.xpath("//*[@class='App-container-inner']/*[contains(@class, 'App-layout-container')][1]"));
    private Element tableElemnt = new Element(sectionElement.getWebElement().findElement(By.xpath(".//table")));
    private ArrayList<Row> table = new ArrayList<>();

    UploadedFilesTable(){
        List<WebElement> allRows = tableElemnt.getWebElement().findElements(By.xpath("./tbody/tr"));

        //initialize all Rows in the table
        for (WebElement webElement :
                allRows) {
            this.table.add(new Row(webElement));
        }
    }

    Row getUploadedFileRow(int rowNumber){
        return table.get(rowNumber - 1);
    }

//    public String getFileName(int rowNumber){
//        return getUploadedFileRow(rowNumber).fileName.getText();
//    }
//
//    public String getProgress(int rowNumber){
//        return getUploadedFileRow(rowNumber).progress.getText();
//    }
//    public String getEmailsUploaded(int rowNumber){
//        return getUploadedFileRow(rowNumber).emailsUploaded.getText();
//    }
//
//    public String getStatus(int rowNumber){
//        return getUploadedFileRow(rowNumber).status.getText();
//    }
//
//    public String getErrorMsg(int rowNumber){
//        return getUploadedFileRow(rowNumber).errorMessage.getText();
//    }


    public class Row {
        Element fileName;
        Element progress;
        Element emailsUploaded;
        Element status;
        Element errorMessage;

        Row(WebElement rowElement){
            fileName = new Element(rowElement.findElement(By.xpath("./td[contains(@class, 'datagrid-file-name')]/div")));
            progress = new Element(rowElement.findElement(By.xpath("./td[contains(@class, 'datagrid-progress')]/div")));
            emailsUploaded = new Element(rowElement.findElement(By.xpath("./td[contains(@class, 'datagrid-uploaded')]")));
            status = new Element(rowElement.findElement(By.xpath("./td[contains(@class, 'datagrid-status')]/div")));
            errorMessage = new Element(rowElement.findElement(By.xpath("./td[contains(@class, 'datagrid-error-message')]")));
        }

        public String getFileName() {
            return fileName.getText();
        }

        public String getProgress() {
            return progress.getText();
        }

        public String getEmailsUploaded() {
            return emailsUploaded.getText();
        }

        public String getStatus() {
            return status.getText();
        }

        public String getErrorMessage() {
            return errorMessage.getText();
        }

    }

}
