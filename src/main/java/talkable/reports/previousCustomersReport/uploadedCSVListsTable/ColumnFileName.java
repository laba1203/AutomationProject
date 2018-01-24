package talkable.reports.previousCustomersReport.uploadedCSVListsTable;

import abstractObjects.AbstractElement;
import org.openqa.selenium.By;

public class ColumnFileName extends AbstractElement{
//    private By locator = By.cssSelector("tbody>tr:nth-child(1)>.datagrid-file-name>div");

    public ColumnFileName(int rowNumber){
        setWebElement(By.cssSelector("tbody>tr:nth-child(" + rowNumber + ")>.datagrid-file-name>div"));
    }
}
