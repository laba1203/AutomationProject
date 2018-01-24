package talkable.reports.previousCustomersReport.uploadedCSVListsTable;

import abstractObjects.AbstractElementsContainer;

class Row extends AbstractElementsContainer{

    ColumnFileName fileName;
    ColumnEmailsUploaded emailsUploaded;
    ColumnProgress progress;
    ColumnStatus status;

    public Row(int rowNumber){
        fileName = new ColumnFileName(rowNumber);
        emailsUploaded = new ColumnEmailsUploaded(rowNumber);
        progress = new ColumnProgress(rowNumber);
        status = new ColumnStatus(rowNumber);
    }

}
