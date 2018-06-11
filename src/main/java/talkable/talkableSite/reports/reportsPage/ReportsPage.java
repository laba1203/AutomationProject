package talkable.talkableSite.reports.reportsPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.talkableSite.reports.referrals.PageReferralsReport;
import talkable.talkableSite.reports.reportsPage.elements.PeopleButton;
import talkable.talkableSite.reports.reportsPage.elements.PreviousCustomersButton;
import talkable.talkableSite.reports.reportsPage.elements.ReferralsButton;
import talkable.talkableSite.reports.rewards.RewardsReportPage;

public class ReportsPage extends AbstractTalkableSitePage{

    private Element rewardsReportButton = new Element(By.xpath("//*[text() = 'Rewards']"), "Rewards Report button");

    private PeopleButton peopleButton = new PeopleButton();
    private ReferralsButton referralsButton = new ReferralsButton();


    public PreviousCustomersReportPage openExistingCustomerReport(){
        new PreviousCustomersButton().click();
        return new PreviousCustomersReportPage();
    }

    public PageReferralsReport openReferralsReport(){
        referralsButton.click();
        return new PageReferralsReport();
    }

    public RewardsReportPage openRewardsReport(){
        rewardsReportButton.click();
        return new RewardsReportPage();
    }
}
