package execution.reports.advocateOffers;

import api.objects.Site;
import api.scenarios.ViaAPI;
import common.cases.CommonScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import talkable.talkableSite.reports.advocateOffers.AdvocateOffersReportPage;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;

import static talkable.common.CampaignPlacement.PostPurchase;
import static talkable.common.CampaignType.Invite;

public class AdvocateOffers extends BaseTest{
    private static final String user = PropertyLoader.loadProperty("talkable.user.reports");
    private static final String pswrd = EnvFactory.getPassword();
    private Site site;

    @BeforeClass
    public void loginAndCreateSite(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(user, pswrd);
        CommonScenarios.openCampaignsPage();
        CommonScenarios.createAndLaunchCampaign(Invite, PostPurchase);
        CommonScenarios.addTagOnCampaignRulesPage("api");
    }

    @BeforeGroups("api-usage")
    public void getApiKey(){
        site = CommonScenarios.getSiteIntegrationValues();
    }

    @Test(groups = "api-usage")
    public void makePurchasesAndVerifyPeopleReport(){
        String email = "automation.email" + TestDataGenerator.getRandomId() + "@gmail.com";
        ViaAPI.makePurchaseWithRandomUUID(site, email);
        ViaAPI.makePurchaseWithRandomUUID(site,"autotest@gmail.com");
        ReportsScenarios.openAdvocateOffersReport();
        ReportsScenarios.searchAdvocateInAdvocateOffersReport(email);
        ReportsScenarios.assertRowsCountFromReport("1", new AdvocateOffersReportPage());
        Assert.assertEquals(
                ReportsScenarios.getEmailFromFirstRowInAdvocateOffersReport(),
                email,
                "Incorrect email in the first row on People report page."
        );
    }
}
