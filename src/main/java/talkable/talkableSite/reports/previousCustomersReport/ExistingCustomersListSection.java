package talkable.talkableSite.reports.previousCustomersReport;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.DriverConfig;

class ExistingCustomersListSection extends AbstractElementsContainer{
    private static final By rowElementXpath = By.xpath("//td[contains(@class, 'datagrid-email')]");
    private static final By searchFieldXpath = By.xpath("//input[@name = 'previous_customers_grid[email]']");
    private static final By filterBtnLctr = By.xpath("//input[@name = 'commit']");
    private static final By firstRowLctr = By.xpath("//tr[1]/td[contains(@class, 'datagrid-email')]");
    private static final By totalLctr = By.xpath("//h1/following-sibling::div//span[@class='base-form-label']");

    private Element searchField;
    private Element filterButton;
    private Element firstResult;
    private Element total;

    private void setElements(){
        searchField = new Element(searchFieldXpath);
        filterButton = new Element(filterBtnLctr);
        firstResult = new Element(firstRowLctr);
    }

    String getTotal(){
        total = new Element(totalLctr);
        return total.getText();
    }

    PreviousCustomersReportPage filter(String email){
        String initialTotal = getTotal();
        searchField = new Element(searchFieldXpath);
        searchField.sendKeys(email);
        filterButton = new Element(filterBtnLctr);
        filterButton.click();

        DriverConfig.getExplicitWait().until(ExpectedConditions.invisibilityOfElementWithText(totalLctr, initialTotal));
        return new PreviousCustomersReportPage();
    }

    String getFirstRowValue(){
        firstResult = new Element(firstRowLctr);
        return firstResult.getText();
    }

}
