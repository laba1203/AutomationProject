package talkable.talkableSite.reports.referrals;

import abstractObjects.AbstractElementsContainer;
import abstractObjects.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import talkable.talkableSite.reports.referrals.referralDetailsPage.ReferralDetailsPage;
import util.WaitFactory;

public class FirstRow extends AbstractElementsContainer{

    private static final By frienEmailLocator = By.xpath("//tbody/tr[1]/td[5]/a");
    private static final By advocateEmailLocator = By.xpath("//tbody/tr[2]/td[2]/a");
    private static final By approvedButtonLctr = By.xpath("//tr[1]/td[@class='action']//a[@title = 'Approved']");
    private static final By voidButtonLctr = By.xpath("//tr[1]/td[@class='action']//a[@title = 'Voided']");
    private static final By detailsLinkLctr = By.xpath("//tr[1]/td[@class='action']//a[text() = 'Details']");
    private static final By referralStatusLctr = By.xpath("//tr[1]/td[@class='action']/div");
    private static final By friendRewardUnpaidReasonLctr = By.xpath("//tr[1]//span[@class='unredeem_reason']");
    private static final By advocateRewardUnpaidReasonLctr = By.xpath("//tr[2]//span[@class='unredeem_reason']");


    public String getAdvocateEmail(){
        return new Element(advocateEmailLocator).getText();
    }

    public String getFriendEmail(){
        return new Element(frienEmailLocator).getText();
    }

    public PageReferralsReport approve(){
        String oldStatus = getRowStatus();
        new Element(approvedButtonLctr).click();
        waitFactory().waitInvisibilityOfElementWithText(referralStatusLctr, oldStatus);
        return new PageReferralsReport();
    }

    public PageReferralsReport voidReferral(){
        String oldStatus = getRowStatus();
        new Element(voidButtonLctr).click();
        waitFactory().waitInvisibilityOfElementWithText(referralStatusLctr, oldStatus);
        return new PageReferralsReport();
    }

    public ReferralDetailsPage openReferralDetails(){
        new Element(detailsLinkLctr).click();
        return new ReferralDetailsPage();
    }

    public String getRowStatus(){
        try {
            return new Element(referralStatusLctr).getText();
        }catch (NotFoundException e){
            new Element(approvedButtonLctr);
            Assert.fail("FAILED: Status is not displayed for the first row in Referrals report. 'Approve' action button is displayed");
            return null;
        }
    }

    public String getFriendRewardUnpaidReason(){
        return new Element(friendRewardUnpaidReasonLctr).getText();
    }

    public String getAdvocateRewardUnpaidReason(){
        return new Element(advocateRewardUnpaidReasonLctr).getText();
    }


}
