package talkable.reports.previousCustomersReport.uploadedCSVListsTable;

import abstractObjects.AbstractElementsContainer;

import java.util.ArrayList;

public class UploadedCSVListsTable extends AbstractElementsContainer{



    public ArrayList<String> getRowValues(int rowNumber){
        Row row = new Row(rowNumber);
        ArrayList<String> values = new <String>ArrayList<String>();

        values.add(row.fileName.getText());
        values.add(row.progress.getText());
        values.add(row.emailsUploaded.getText());
        values.add(row.status.getText());

        return values;
    }

    public void waitTillProgressPopulated(String text){
        new Row(1).waitTillProgressPopulated(text);
    }

    public void waitTillProgressUnpopulated(String text){
        new Row(1).waitTillTextDisappearedInProgress(text);
    }

    public ColumnProgress getProgressElement(int rowNumber){
        return new Row(rowNumber).progress;
    }


    public String getFileName(int rowNumber){
        return new Row(rowNumber).fileName.getText();
    }

    public String getProgress(int rowNumber){
        return new Row(rowNumber).progress.getText();
    }

    public String getEmailsUploaded(int rowNumber){
        return new Row(rowNumber).emailsUploaded.getText();
    }

    public String getStatus(int rowNumber){
        return new Row(rowNumber).status.getText();
    }




}
