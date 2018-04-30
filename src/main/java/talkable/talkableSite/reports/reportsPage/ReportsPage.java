package talkable.talkableSite.reports.reportsPage;

import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.talkableSite.reports.referralsReport.PageReferralsReport;
import talkable.talkableSite.reports.reportsPage.elements.PeopleButton;
import talkable.talkableSite.reports.reportsPage.elements.PreviousCustomersButton;
import talkable.talkableSite.reports.reportsPage.elements.ReferralsButton;

public class ReportsPage extends AbstractTalkableSitePage{

    private static final String title = "Support & Reports | Talkable";

    private PeopleButton peopleButton;
    private ReferralsButton referralsButton;



    public ReportsPage(){
        peopleButton = new PeopleButton();
        referralsButton = new ReferralsButton();

    }

    public PreviousCustomersReportPage openExistingCustomerReport(){
        new PreviousCustomersButton().click();
        return new PreviousCustomersReportPage();
    }
    public PageReferralsReport openReferralsReport(){
        referralsButton.click();
        return new PageReferralsReport();
    }
}
