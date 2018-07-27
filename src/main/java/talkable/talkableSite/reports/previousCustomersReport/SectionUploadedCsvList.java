package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.common.elements.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public class SectionUploadedCsvList extends AbstractElementsContainer{

    private static final String sectionXpath = "//*[@class='App-container-inner']/*[contains(@class, 'App-layout-container')][1]";
    private static final By firstPaginationLctr = By.xpath(sectionXpath + "//div[@class = 'pagination '][1]/ul");
    private static final By secondPaginationLctr = By.xpath(sectionXpath + "//div[@class = 'pagination '][2]/ul");

    private Element sectionElement = new Element(By.xpath(sectionXpath));
    private ArrayList<Row> table = new ArrayList<>();


    SectionUploadedCsvList(){
        Element tableElemnt = new Element(sectionElement.getWebElement().findElement(By.xpath(".//table")));
        List<WebElement> allRows = tableElemnt.getWebElement().findElements(By.xpath("./tbody/tr"));

        //initialize all Rows in the table
        for (WebElement webElement :
                allRows) {
            this.table.add(new Row(webElement));
        }
    }

    Row getFirstRow(){
        return table.get(0);
    }

    public Pagination getTopPagination(){
        return new Pagination(firstPaginationLctr);
    }

    public Pagination getBottomPagination(){
        return new Pagination(secondPaginationLctr);
    }

    public class Row {

        private final By progressLctr = By.xpath("./td[contains(@class, 'datagrid-progress')]/div");
        private final By fileNameLctr = By.xpath("./td[contains(@class, 'datagrid-file-name')]/div");
        private final By emailsUploadedLctr = By.xpath("./td[contains(@class, 'datagrid-uploaded')]");
        private final By statusLctr = By.xpath("./td[contains(@class, 'datagrid-status')]/div");
        private final By errorMsgLctr = By.xpath("./td[contains(@class, 'datagrid-error-message')]");
        private WebElement parentElement;
        Element fileName;
//        Element progress;
//        Element emailsUploaded;
//        Element status;
//        Element errorMessage;

        Row(WebElement rowElement){
            parentElement = rowElement;
            fileName = new Element(rowElement.findElement(fileNameLctr));
//            progress = new Element(rowElement.findElement(progressLctr));
//            emailsUploaded = new Element(rowElement.findElement(emailsUploadedLctr));
//            status = new Element(rowElement.findElement(statusLctr));
//            errorMessage = new Element(rowElement.findElement(errorMsgLctr));
        }

        public String getFileName() {
            return new Element(parentElement.findElement(fileNameLctr)).getText();
        }

        public String getProgress() {
            return new Element(parentElement.findElement(progressLctr)).getText();
        }

        public String getEmailsUploaded() {
            return new Element(parentElement.findElement(emailsUploadedLctr)).getText();
        }

        public String getStatus() {
            return new Element(parentElement.findElement(statusLctr)).getText();
        }

        public String getErrorMessage() {
            return new Element(parentElement.findElement(errorMsgLctr)).getText();
        }



    }

}
