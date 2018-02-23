package talkable.talkableSite.reports.previousCustomersReport.uploadedCSVListsTable;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

class ColumnStatus extends AbstractElement{
//    private By locator = By.cssSelector("tbody>tr:nth-child(1)>.datagrid-file-name>div");

    public ColumnStatus(int rowNumber){
        setWebElement(By.cssSelector("tbody>tr:nth-child(" + rowNumber + ")>.datagrid-status>div"));
    }
}
