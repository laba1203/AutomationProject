package talkable.reports.reportsPage;

import abstractObjects.AbstractElementsContainer;
import talkable.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.reports.reportsPage.elements.PeopleButton;
import talkable.reports.reportsPage.elements.PreviousCustomersButton;
import talkable.reports.reportsPage.elements.ReferralsButton;

public class ReportsPage extends AbstractElementsContainer{

    private static final String title = "Support & Reports | Talkable";

    private PreviousCustomersButton previousCustomersButton;
    private PeopleButton peopleButton;
    private ReferralsButton referralsButton;



    public ReportsPage(){
        isPageOpened(title);

        previousCustomersButton = new PreviousCustomersButton();
        peopleButton = new PeopleButton();
        referralsButton = new ReferralsButton();

    }

    public PreviousCustomersReportPage openExistingCustomerReport(){
        previousCustomersButton.click();
        return new PreviousCustomersReportPage();
    }
}
