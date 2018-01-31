package talkable.reports.previousCustomersReport.uploadedCSVListsTable;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.support.ui.ExpectedConditions;

class Row extends AbstractElementsContainer{

    ColumnFileName fileName;
    ColumnEmailsUploaded emailsUploaded;
    ColumnProgress progress;
    ColumnStatus status;

    Row(int rowNumber){
        fileName = new ColumnFileName(rowNumber);
        emailsUploaded = new ColumnEmailsUploaded(rowNumber);
        progress = new ColumnProgress(rowNumber);
        status = new ColumnStatus(rowNumber);
    }

    public void waitTillProgressPopulated(String text){
        progress.waitTillElementPopulatedByText(text);
    }

    public void waitTillTextDisappearedInProgress(String text){
        progress.waitElementWithTextDisappeared(text);
    }

}
