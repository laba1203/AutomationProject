import common.cases.CommonScenarios;
import execution.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.siteSettings.SiteSettingsPage;
import util.EnvFactory;
import util.PropertyLoader;

public class Sandbox extends BaseTest {

    @BeforeClass
    public void login(){
        String email = PropertyLoader.loadProperty("talkable.user.siteSettings");
        String password = EnvFactory.getPassword();

        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(email, password );
    }

    @Test
    public void discardSiteSettingsContactChanges(){

        String csEmail = "cs_email@dot.com";

        CommonScenarios.openSiteSettingsPage().openContactsTab();
        String expectedEmail = CommonScenarios.getSiteCSemail();
        CommonScenarios.populateSiteSettingsContactsTab(csEmail);
        CommonScenarios.swithUnsavedTab(SiteSettingsPage.SiteSettingsTab.BASIC);
        CommonScenarios.discardUnsavedchanges();

        CommonScenarios.assertDiscardedChangesSiteSettigsContactsTab(expectedEmail);
    }
}
