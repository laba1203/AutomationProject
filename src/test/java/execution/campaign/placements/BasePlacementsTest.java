package execution.campaign.placements;

import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.annotations.*;
import talkable.talkableSite.headerFrame.Header;
import util.EnvFactory;
import util.PropertyLoader;


public class BasePlacementsTest extends BaseTest{

//    private static final String siteName = PropertyLoader.loadProperty("sites.name.campaign.placements.test");
    protected static final String SITE_URL = PropertyLoader.loadEnvProperty("test.sites.campaign.placements");
    private static final String USER_EMAIL = PropertyLoader.loadProperty("user.login.campaign.placements");

    @BeforeSuite
    public void precondition(){
        //login to Talkable
        driver.navigate().to(EnvFactory.getAdminUrl());
        CommonScenarios.login(USER_EMAIL, EnvFactory.getPassword());
    }



    @AfterSuite
    public void logout(){
        new Header().logout();
    }

}
