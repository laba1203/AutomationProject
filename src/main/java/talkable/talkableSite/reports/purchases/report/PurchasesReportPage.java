package talkable.talkableSite.reports.purchases.report;

import abstractObjects.Element;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.By;
import talkable.talkableSite.reports.AbstractReportPage;
import talkable.talkableSite.reports.CountableReport;
import talkable.talkableSite.reports.FilterableReport;


public class PurchasesReportPage extends AbstractReportPage implements CountableReport, FilterableReport{
    private static final String firstRowXpath = "//table[1]/tbody/tr[1]";

    private static final By emailFromFirstRowLctr = By.xpath(firstRowXpath + "//*[contains(@class, 'email')]");
    private static final By advancedFilterLctr = By.xpath("//div[contains(@class, 'panel-group')][1]//h6//a");
    private static final By emailSearchField = By.xpath("//input[@name='origins_grid[email]']");

    private Element advancedFilterBtn = new Element(advancedFilterLctr, "Advanced Filters");

    public String getEmailFromFirstRow(){
        return new Element(emailFromFirstRowLctr).getText();
    }

    public PurchasesReportPage searchByEmail(String email){
        advancedFilterBtn.click();
        new Element(emailSearchField, "Email Search field").sendKeys(email);
        generate();
        return new PurchasesReportPage();
    }

}
