package talkable.talkableSite.reports.reportsPage;

import abstractObjects.Element;
import org.openqa.selenium.By;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.reports.couponLists.CouponListsReportPage;
import talkable.talkableSite.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.talkableSite.reports.referrals.PageReferralsReport;
import talkable.talkableSite.reports.rewards.RewardsReportPage;
import util.logging.Log;

public class ReportsPage extends AbstractTalkableSitePage{

    private static final By referralsLctr = By.cssSelector("a[href$='referrals']");
    private static final By peopleLctr = By.cssSelector("a[href$='people']");
    private static final By rewardsReportBtnLctr = By.xpath("//*[text() = 'Rewards']");
    private static final By couponListsBtnLctr = By.xpath("//*[text() = 'Coupon Lists']");
    private static final By previousCustomerBtnLctr = By.cssSelector("a[href$='previous_customers']");

    private Element rewardsReportButton = new Element(rewardsReportBtnLctr, "Rewards Report button");
    private Element couponListsBtn = new Element(couponListsBtnLctr, "'Coupon Lists' report button");
    private Element referralsBtn = new Element(referralsLctr, "'Referrals' report button");
    private Element existingCustomersBtn = new Element(previousCustomerBtnLctr, "'Existing Customer' report button");
    private Element peopleBtn = new Element(peopleLctr, "'People' report button");


    public PreviousCustomersReportPage openExistingCustomerReport(){
        existingCustomersBtn.click();
        return new PreviousCustomersReportPage();
    }

    public PageReferralsReport openReferralsReport(){
        referralsBtn.click();
        return new PageReferralsReport();
    }

    public RewardsReportPage openRewardsReport(){
        rewardsReportButton.click();
        return new RewardsReportPage();
    }

    public CouponListsReportPage openCouponsListReport(){
        couponListsBtn.click();
        CouponListsReportPage page = new CouponListsReportPage();
        Log.logRecord("Coupon Lists report is opened.");
        return page;
    }
}
