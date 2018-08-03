package execution.reports.purchases;

import api.objects.Site;
import api.scenarios.ViaAPI;
import common.cases.CommonScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import talkable.talkableSite.reports.peopleReport.PeopleReportPage;
import talkable.talkableSite.reports.purchases.report.PurchasesReportPage;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;

public class PurchasesReport extends BaseTest{
    private static final String user = PropertyLoader.loadProperty("talkable.user.reports");
    private static final String pswrd = EnvFactory.getPassword();
    private Site site;

    @BeforeClass
    public void loginAndCreateSite(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(user, pswrd);
    }

    @BeforeGroups("api-usage")
    public void getApiKey(){
        site = CommonScenarios.getSiteIntegrationValues();
    }

    @Test(groups = "api-usage")
    public void makePurchasesAndVerifyPurchasesReport(){
        String email = "automation.email" + TestDataGenerator.getRandomId() + "@gmail.com";
        ViaAPI.makePurchaseWithRandomUUID(site, email);
        ViaAPI.makePurchaseWithRandomUUID(site,"autotest1@gmail.com");
        ViaAPI.makePurchaseWithRandomUUID(site,"autotest2@gmail.com");
        ReportsScenarios.openPurchasesReport();
        ReportsScenarios.searchPurchaseInPurchasesReport(email);
        ReportsScenarios.assertRowsCountFromReport("1", new PurchasesReportPage());
        Assert.assertEquals(
                ReportsScenarios.getEmailFromFirstRowInPurchasesReport(),
                email,
                "Incorrect email in the first row on People report page."
        );
    }
}
