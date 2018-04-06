package execution.campaign.placements;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.siteDashboardPage.SiteDashboardPage;
import util.EnvFactory;
import util.PropertyLoader;

import static talkable.common.CampaignPlacement.Standalone;
import static talkable.common.CampaignType.Invite;

public class FloatingWidgetPlacementsTest extends BaseTest{

    private static final String siteName = PropertyLoader.loadProperty("sites.name.campaign.placements.test");
    private static final String SITE_URL = PropertyLoader.loadEnvProperty("test.sites.campaign.placements");

    @BeforeClass
    public void precondition(){
        //login to Talkable and select site
        CommonScenarios.login(EnvFactory.getUser(), EnvFactory.getPassword());
        SiteDashboardPage dashboardPage = CommonScenarios.switchToSiteByVisibleText(siteName);
        //Create new Campaign
        dashboardPage.header.openCampaignsPage().createNewCampaign(Invite, Standalone);
    }

    @Test
    public void test1(){

    }

}
