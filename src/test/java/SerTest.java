

import common.cases.CommonScenarios;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.customerService.createReferral.PageCreateReferral;
import talkable.talkableSite.headerFrame.Header;
import talkable.talkableSite.reports.referralsReport.PageReferralsReport;
import talkable.talkableSite.reports.reportsPage.ReportsPage;
import util.DriverConfig;

public class SerTest {
    WebDriver driver;
    CommonScenarios commonScenarios = new CommonScenarios();

    @BeforeClass
    public void setup() {
        driver = new DriverConfig().getDriver();
        driver.navigate().to("https://void.talkable.com/");
    }
//qweq123123
    @Test
    public void test1(){
            commonScenarios.login("hutornoy@talkable.com", "organ_Telo23");
            Header header = new Header();
            ReportsPage report = header.clickReportsButton();
            PageReferralsReport referralsReport = report.openReferralsReport();
            PageCreateReferral createReferral = referralsReport.clickCreateReferral();
    }

}
