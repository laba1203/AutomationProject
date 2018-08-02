package talkable.talkableSite.reports.staticAssets;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import talkable.common.elements.alert.Alert;
import talkable.talkableSite.reports.AbstractReportPage;
import talkable.talkableSite.reports.CountableReport;
import util.logging.Log;

import java.util.ArrayList;

public class StaticAssetsReportPage extends AbstractReportPage implements CountableReport{
    private static final String header = "Static Assets";
    private static final String rowsXpath = "//table[contains(@class, 'static-assets-list')]/tbody/tr";

    private static final By searchInputLctr = By.xpath("//*[@name='static_assets_grid[name]']");
    private static final By filterBtnLctr = By.xpath("//*[@name='commit']");
    private static final By rowsLctr = By.xpath(rowsXpath);
    private static final By firstRowLctr = By.xpath(rowsXpath + "[1]");
    private static final By deleteBtnLctr = By.xpath("//*[contains(@class, 'static-assets-delete-btn')]");
    private static final By totalCountLctr = By.xpath("//*[contains(@class, 'base-form-label')]");

    private Element searchInput = new Element(searchInputLctr, "Search field");
    private Element filterBtn = new Element(filterBtnLctr, "Filter button");


    public StaticAssetsReportPage(){
        verifyHeader(header);
    }

    public StaticAssetsReportPage filterByName(String name){
        searchInput.sendKeys(name);
        filterBtn.click();
        return new StaticAssetsReportPage();
    }

    public StaticAssetsReportPage deleteAllRows(){
        int rowsCount = getRows().size();
        for (Row row :
                getRows()) {
            row.selectDeleteCheckbox();
        }
        new Element(deleteBtnLctr, "Delete button").click();
        new Alert()
                .verifyAlertMsg("Are you sure?")
                .confirm();
        Log.logRecord("<" + rowsCount +"> rows deleted from Static Asserts report.");
        return new StaticAssetsReportPage();
    }

    private Row getFirstRow(){
        return new Row(firstRowLctr);
    }

    private ArrayList<Row> getRows(){
        ArrayList<Row> rows = new ArrayList<>();
        for (WebElement rowEl:
                driver.findElements(rowsLctr)) {
            rows.add(new Row(rowEl));
        }
        return rows;
    }

    @Override
    public String getTotalCount() {
        String value = new Element(totalCountLctr).getText();
        if(value.equals("Not found")){
            return "0";
        }
        return value.substring(7);
    }


    private class Row{
        private final By deleteCheckboxLctr = By.xpath(".//*[@name='delete']");

        private WebElement ownElement;

        private Row(WebElement rowElement){
            ownElement = rowElement;
        }

        private Row(By locator){
            ownElement = driver.findElement(locator);
        }

        void selectDeleteCheckbox(){
            new Element(ownElement, deleteCheckboxLctr, "Delete checkbox")
                    .click();
        }


    }


}
