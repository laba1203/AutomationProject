package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import talkable.common.elements.pagination.Pagination;
import util.DriverConfig;
import util.WaitFactory;

class ExistingCustomersListSection extends AbstractElementsContainer{
    private static final By rowElementXpath = By.xpath("//td[contains(@class, 'datagrid-email')]");
    private static final By searchFieldXpath = By.xpath("//input[@name = 'previous_customers_grid[email]']");
    private static final By filterBtnLctr = By.xpath("//input[@name = 'commit']");
    private static final By firstRowLctr = By.xpath("//tr[1]/td[contains(@class, 'datagrid-email')]");
    private static final By totalLctr = By.xpath("//h1/following-sibling::div//span[@class='base-form-label']");
    private static final By topPaginationLctr = By.xpath("//h1[contains(text(), 'Existing Customers List')]/following::div/div[1]/ul");
    private static final By bottomPaginationLctr = By.xpath("//h1[contains(text(), 'Existing Customers List')]/following::div/div[2]/ul");

    private Element searchField;
    private Element filterButton;
    private Element firstResult;

    ExistingCustomersListSection(){
//        beforeSupperActions();
    }

    private void setElements(){
        searchField = new Element(searchFieldXpath);
        filterButton = new Element(filterBtnLctr);
        firstResult = new Element(firstRowLctr);
    }

    String getTotal(){
        Element total = new Element(totalLctr);
        return total.getText();
    }

    PreviousCustomersReportPage filter(String email){
        String initialTotal = getTotal();
        searchField = new Element(searchFieldXpath);
        searchField.clear();
        searchField.sendKeys(email);
        filterButton = new Element(filterBtnLctr);
        filterButton.click();

        try {
            waitFactory().getExplicitWait().until(ExpectedConditions.invisibilityOfElementWithText(totalLctr, initialTotal));
        }catch (TimeoutException e){
            Assert.fail("FAILED: Total value is not changed after filtering. \r\nFull log: " + e.getMessage());
        }
        return new PreviousCustomersReportPage();
    }

    String getFirstRowValue(){
        firstResult = new Element(firstRowLctr);
        return firstResult.getText();
    }
    Pagination getTopPagination(){
        return new Pagination(topPaginationLctr);
    }

    Pagination getBottomPagination(){
        return new Pagination(bottomPaginationLctr);
    }

}
