package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.common.elements.pagination.Pagination;

public class SectionUploadedCsvList extends AbstractElementsContainer{

    private static final String sectionXpath = "//*[@class='App-container-inner']/*[contains(@class, 'App-layout-container')][1]";
    private static final String tableXpath = sectionXpath + "//table";
    private static final By tableLctr = By.xpath(tableXpath);
    private static final By firstPaginationLctr = By.xpath(sectionXpath + "//div[@class = 'pagination '][1]/ul");
    private static final By secondPaginationLctr = By.xpath(sectionXpath + "//div[@class = 'pagination '][2]/ul");


    SectionUploadedCsvList(){
        new Element(tableLctr);
    }

    FirstRow getFirstRow(){
        return new FirstRow();
    }

    public Pagination getTopPagination(){
        return new Pagination(firstPaginationLctr);
    }

    public Pagination getBottomPagination(){
        return new Pagination(secondPaginationLctr);
    }

    public class FirstRow {

//        private final By progressLctr = By.xpath("./td[contains(@class, 'datagrid-progress')]/div");
        private final By progressLctr = By.xpath(tableXpath + "//tr[1]/td[contains(@class, 'datagrid-progress')]/div");
//        private final By fileNameLctr = By.xpath("./td[contains(@class, 'datagrid-file-name')]/div");
        private final By fileNameLctr = By.xpath(tableXpath + "//tr[1]/td[contains(@class, 'datagrid-file-name')]/div");
//        private final By emailsUploadedLctr = By.xpath("./td[contains(@class, 'datagrid-uploaded')]");
        private final By emailsUploadedLctr = By.xpath(tableXpath + "//tr[1]/td[contains(@class, 'datagrid-uploaded')]");
//        private final By statusLctr = By.xpath("./td[contains(@class, 'datagrid-status')]/div");
        private final By statusLctr = By.xpath(tableXpath + "//tr[1]/td[contains(@class, 'datagrid-status')]/div");
//        private final By errorMsgLctr = By.xpath("./td[contains(@class, 'datagrid-error-message')]");
        private final By errorMsgLctr = By.xpath(tableXpath + "//tr[1]/td[contains(@class, 'datagrid-error-message')]");

        private Element fileName = new Element(fileNameLctr);
        private Element progress = new Element(progressLctr);
        private Element emailsUploaded = new Element(emailsUploadedLctr);
        private Element status = new Element(statusLctr);
        private Element errorMsg = new Element(errorMsgLctr);


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
            return errorMsg.getText();
        }

    }

}
