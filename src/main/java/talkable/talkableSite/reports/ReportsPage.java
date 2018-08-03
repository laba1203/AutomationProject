package talkable.talkableSite.reports;

import abstractObjects.Element;
import org.openqa.selenium.By;
import org.testng.Assert;
import talkable.access.managment.access.request.AccessRequestPage;
import talkable.talkableSite.AbstractTalkableSitePage;
import talkable.talkableSite.reports.abTests.AbTestsReportPage;
import talkable.talkableSite.reports.couponLists.CouponListsReportPage;
import talkable.talkableSite.reports.emailConversion.EmailConversionReportPage;
import talkable.talkableSite.reports.emailPerformance.EmailPerformanceReportPage;
import talkable.talkableSite.reports.localeEntries.LocaleEntriesReportPage;
import talkable.talkableSite.reports.metricsAggregation.MetricsAggregationReportPage;
import talkable.talkableSite.reports.pageSnapshots.PageSnapshotsReportPage;
import talkable.talkableSite.reports.peopleReport.PeopleReportPage;
import talkable.talkableSite.reports.performanceByChannel.PerformanceByChannelReportPage;
import talkable.talkableSite.reports.performanceOverTime.PerformanceOverTimeReportPage;
import talkable.talkableSite.reports.previousCustomersReport.PreviousCustomersReportPage;
import talkable.talkableSite.reports.referrals.PageReferralsReport;
import talkable.talkableSite.reports.referralsOverTime.ReferralsOverTimeReportPage;
import talkable.talkableSite.reports.rewards.RewardsReportPage;
import talkable.talkableSite.reports.settingsChanges.SettingsChangesReportPage;
import talkable.talkableSite.reports.staticAssets.StaticAssetsReportPage;
import talkable.talkableSite.reports.summaryReport.SummaryReportPage;
import talkable.talkableSite.reports.weeklyStats.WeeklyStatsReportPage;
import util.logging.Log;

public class ReportsPage extends AbstractTalkableSitePage{

    private static final By referralsLctr = By.cssSelector("a[href$='referrals']");
    private static final By peopleLctr = By.cssSelector("a[href$='people']");
    private static final By rewardsReportBtnLctr = By.xpath("//*[text() = 'Rewards']");
    private static final By couponListsBtnLctr = By.xpath("//*[text() = 'Coupon Lists']");
    private static final By previousCustomerBtnLctr = By.cssSelector("a[href$='previous_customers']");
    private static final By purchasesReportBtnLctr = By.xpath("//*[text() = 'Purchases']");
    private static final By eventsLctr = By.xpath("//*[text() = 'Events']");
    private static final By advocateOffersLctr = By.xpath("//*[text() = 'Advocate Offers']");
    private static final By offerSharesLctr = By.xpath("//*[text() = 'Offer Shares']");
    private static final By friendOffersLctr = By.xpath("//*[text() = 'Friend Offers (aka Cookies)']");
    private static final By customerEmailLettersBtnLctr = By.xpath("//*[text() = 'Customer Email Letters']");
    private static final By topAdvocatesBtnLctr = By.xpath("//*[text() = 'Top Advocates']");
    private static final By staticAssetsBtnLctr = By.xpath("//*[text() = 'Static Assets']");
    private static final By localeEntriesBtnLctr = By.xpath("//*[text() = 'Locale entries']");
    private static final By settingsChangesBtnLctr = By.xpath("//*[text() = 'Settings Changes']");
    private static final By performanceByChannelBtnLctr = By.xpath("//*[text() = 'Performance by Channel']");
    private static final By weeklyStatsBtnLctr = By.xpath("//*[text() = 'Weekly Stats']");
    private static final By summaryReportBtnLctr = By.xpath("//*[text() = 'Summary Report']");
    private static final By emailPerformanceBtnLctr = By.xpath("//*[text() = 'Email Performance']");
    private static final By performanceOverTimeBtnLctr = By.xpath("//*[text() = 'Performance Over Time']");
    private static final By metricsAggregationBtnLctr = By.xpath("//*[text() = 'Metrics Aggregation']");
    private static final By abTestsBtnLctr = By.xpath("//*[text() = 'A/B Tests']");
    private static final By referralsOverTimeLctr = By.xpath("//*[text() = 'Referrals Over Time']");
    private static final By emailConversionLctr = By.xpath("//*[text() = 'Email Conversion']");
    private static final By pageSnapshotsLctr = By.xpath("//*[text() = 'Page Snapshots']");


    private Element rewardsReportButton = new Element(rewardsReportBtnLctr, "Rewards Report button");
    private Element couponListsBtn = new Element(couponListsBtnLctr, "'Coupon Lists' report button");
    private Element referralsBtn = new Element(referralsLctr, "'Referrals' report button");
    private Element existingCustomersBtn = new Element(previousCustomerBtnLctr, "'Existing Customer' report button");
    private Element peopleBtn = new Element(peopleLctr, "'People' report button");
    private Element purchasesBtn = new Element(purchasesReportBtnLctr, "'Purchases' report button");
    private Element eventsBtn = new Element(eventsLctr, "'Events' report button");
    private Element advocateOffersBtn = new Element(advocateOffersLctr, "'Advocate Offers' report button");
    private Element offerSharesBtn = new Element(offerSharesLctr, "'Offer Shares' report button");
    private Element friendOffersBtn = new Element(friendOffersLctr, "'Friend Offers (aka Cookies)' report button");
    private Element customerEmailLettersBtn = new Element(customerEmailLettersBtnLctr, "'Customer Email Letters' report button");
    private Element topAdvocatesBtn = new Element(topAdvocatesBtnLctr, "'Top Advocates' report button");
    private Element staticAssetsBtn = new Element(staticAssetsBtnLctr, "'Static Assets'");
    private Element localeEntriesBtn = new Element(localeEntriesBtnLctr, "Locale entries");
    private Element settingsChangesBtn = new Element(settingsChangesBtnLctr, "Settings Changes");
    private Element performanceByChannelBtn = new Element(performanceByChannelBtnLctr, "Performance By Channel");
    private Element weeklyStatsBtn = new Element(weeklyStatsBtnLctr, "Weekly Stats");
    private Element summaryReportBtn = new Element(summaryReportBtnLctr, "Summary Report");
    private Element emailPerformanceBtn = new Element(emailPerformanceBtnLctr, "Email Performance");
    private Element performanceOverTimeBtn = new Element(performanceOverTimeBtnLctr, "Performance Over Time");
    private Element metricsAggregationBtn = new Element(metricsAggregationBtnLctr, "Metrics Aggregation");
    private Element abTestsBtn = new Element(abTestsBtnLctr, "A/B Tests");



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
        logReportOpened("Coupon Lists");
        return page;
    }

    public StaticAssetsReportPage openStaticAssetsReport(){
        staticAssetsBtn.click();
        StaticAssetsReportPage page = new StaticAssetsReportPage();
        logReportOpened("Static Assets");
        return page;
    }

    public LocaleEntriesReportPage openLocaleEntriesReport(){
        localeEntriesBtn.click();
        LocaleEntriesReportPage page = new LocaleEntriesReportPage();
        logReportOpened("Locale Entries");
        return page;
    }

    public SettingsChangesReportPage openSettingsChangesReport(){
        settingsChangesBtn.click();
        SettingsChangesReportPage page = new SettingsChangesReportPage();
        logReportOpened("Settings Changes");
        return page;
    }

    public PerformanceByChannelReportPage openPerformanceByChannelReport(){
        performanceByChannelBtn.click();
        PerformanceByChannelReportPage page = new PerformanceByChannelReportPage();
        logReportOpened("Performance By Channel");
        return page;
    }

    public WeeklyStatsReportPage openWeeklyStatsReport(){
        weeklyStatsBtn.click();
        WeeklyStatsReportPage page = new WeeklyStatsReportPage();
        logReportOpened("Weekly Stats");
        return page;
    }

    public SummaryReportPage openSummaryReport(){
        summaryReportBtn.click();
        SummaryReportPage page = new SummaryReportPage();
        logReportOpened("Summary");
        return page;
    }

    public EmailPerformanceReportPage openEmailPerformanceReport(){
        emailPerformanceBtn.click();
        EmailPerformanceReportPage page = new EmailPerformanceReportPage();
        logReportOpened("Email Performance");
        return page;
    }

    public PerformanceOverTimeReportPage openPerformanceOverTimeReport(){
        performanceOverTimeBtn.click();
        PerformanceOverTimeReportPage page = new PerformanceOverTimeReportPage();
        logReportOpened("Performance Over Time");
        return page;
    }

    public MetricsAggregationReportPage openMetricsAggregationReport(){
        metricsAggregationBtn.click();
        MetricsAggregationReportPage page = new MetricsAggregationReportPage();
        logReportOpened("Metrics Aggregation");
        return page;
    }

    public AbTestsReportPage openAbTestsReport(){
        abTestsBtn.click();
        AbTestsReportPage page = new AbTestsReportPage();
        logReportOpened("A/B Tests");
        return page;
    }

    public ReferralsOverTimeReportPage openReferralsOverTimeReport(){
        new Element(referralsOverTimeLctr, "Referrals Over Time").click();
        ReferralsOverTimeReportPage page = new ReferralsOverTimeReportPage();
        logReportOpened("Referrals Over Time");
        return page;
    }

    public EmailConversionReportPage openEmailConversionReport(){
        new Element(emailConversionLctr, "Email Conversion").click();
        EmailConversionReportPage page = new EmailConversionReportPage();
        logReportOpened("Email Conversion");
        return page;
    }

    public PageSnapshotsReportPage openPageSnapshotsReport(){
        new Element(pageSnapshotsLctr, "Page Snapshots").click();
        PageSnapshotsReportPage page = new PageSnapshotsReportPage();
        logReportOpened("Page Snapshots");
        return page;
    }

    public PeopleReportPage openPeopleReport(){
        peopleBtn.click();
        PeopleReportPage page = new PeopleReportPage();
        logReportOpened("People");
        return page;
    }


    private void logReportOpened(String reportName){
        Log.logRecord(reportName + " report is opened.");
    }

    public AccessRequestPage openReportWithPiiDataUnderNonPiiAccess(String reportName){
        switch (reportName){
            default:
                Assert.fail("Unknown report name <" + reportName + "> was passed into ReportsPage.openReportWithPiiUnderNonPiiAccess() . Please verify whether the report contains PII data.");
                break;
            case "People":
                peopleBtn.click();
                break;
            case "Purchases":
                purchasesBtn.click();
                break;
            case "Events":
                eventsBtn.click();
                break;
            case "Advocate Offers":
                advocateOffersBtn.click();
                break;
            case "Offer Shares":
                offerSharesBtn.click();
                break;
            case "Friend Offers":
                friendOffersBtn.click();
                break;
            case "Referrals":
                referralsBtn.click();
                break;
            case "Rewards":
                rewardsReportButton.click();
                break;
            case "Customer Email Letters":
                customerEmailLettersBtn.click();
                break;
            case "Top Advocates":
                topAdvocatesBtn.click();
                break;
            case "Existing Customers":
                existingCustomersBtn.click();
                break;
        }
        return new AccessRequestPage().verifyPiiAccessRequiredMsg();
    }

}
