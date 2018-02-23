package talkable.reports.referralsReport;

import abstractObjects.AbstractElementsContainer;
import org.openqa.selenium.By;

class RowResult extends AbstractElementsContainer{



    private By frienEmailLocator = By.xpath("//tbody/tr[1]/td[5]/a");
    private By advocateEmailLocator = By.xpath("//tbody/tr[2]/td[2]/a");

    private ElmntRowItem advocateEmail;
    private ElmntRowItem friendEmail;

    RowResult(){
        advocateEmail = new ElmntRowItem(advocateEmailLocator);
        friendEmail = new ElmntRowItem(frienEmailLocator);
    }

    private String getAdvocateEmail(){
        return advocateEmail.getText();
    }
    private String getFriendEmail(){
        return friendEmail.getText();
    }



}
