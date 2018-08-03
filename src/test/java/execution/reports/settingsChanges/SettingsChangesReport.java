package execution.reports.settingsChanges;

import common.cases.CommonScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talkable.talkableSite.reports.settingsChanges.SettingsChangesReportPage;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.Util;

public class SettingsChangesReport extends BaseTest{
    private static final String user = PropertyLoader.loadProperty("talkable.user.reports");
    private static final String pswrd = EnvFactory.getPassword();

    @BeforeClass
    public void loginAndCreateSite(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(user, pswrd);
    }

    @Test
    public void modifySiteNameAndCheckReport(){
        String newSiteName = "automation-" + TestDataGenerator.getRandomId();
        String currentDate = Util.getCurrentDate();
        String expectedModifiedField = "Site name:";
        String oldValue;

        CommonScenarios.openSiteSettingsPage();
        oldValue = CommonScenarios.getSiteNameFromHeader();
        CommonScenarios.updateSiteNameOnSiteSettings(newSiteName);
        ReportsScenarios.openSettingsChangesReport();
        ReportsScenarios.filterReportByDate(new SettingsChangesReportPage(), currentDate, currentDate);
        ReportsScenarios.assertFirstRowInSettingsChangesReport(expectedModifiedField, oldValue, newSiteName);
    }


}
