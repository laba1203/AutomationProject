package talkable.talkableSite.reports.peopleReport;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.reports.AbstractReportPage;
import talkable.talkableSite.reports.CountableReport;
import talkable.talkableSite.reports.FilterableReport;

public class PeopleReportPage extends AbstractReportPage implements CountableReport, FilterableReport {

    private static final String firstRowXpath = "//tbody/tr[1]";
    private static final By emailSearchFieldLctr = By.xpath("//*[@name='people_grid[identifier]']");
    private static final By emailFromFirstRowLctr = By.xpath(firstRowXpath + "//*[contains(@class, 'email')]/a");


    private Element emailSearchField = new Element(emailSearchFieldLctr, "Email, Username, or ID search field");


    public PeopleReportPage search(String email){
        emailSearchField.sendKeys(email);
        generate();
        return new PeopleReportPage();
    }

    public String getEmailFromFirstRow(){
        return new Element(emailFromFirstRowLctr).getText();
    }

}
