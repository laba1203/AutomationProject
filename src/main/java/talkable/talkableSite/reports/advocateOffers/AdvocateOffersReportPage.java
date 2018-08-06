package talkable.talkableSite.reports.advocateOffers;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.reports.AbstractReportPage;
import talkable.talkableSite.reports.CountableReport;
import talkable.talkableSite.reports.FilterableReport;

public class AdvocateOffersReportPage extends AbstractReportPage implements CountableReport, FilterableReport{
    private static final By emailSearchLctr = By.xpath("//input[@name='offers_grid[email]']");
    private static final By emailFromFistRowLctr = By.xpath("//tr[1]/td[contains(@class, 'email')]");
    private Element emailSearch = new Element(emailSearchLctr, "Email search field");

    public AdvocateOffersReportPage searchByEmail(String email){
        emailSearch.sendKeys(email);
        generate();
        return new AdvocateOffersReportPage();
    }

    public String getEmailFromFirstRow(){
        return new Element(emailFromFistRowLctr).getText();
    }
}
