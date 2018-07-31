package execution.access.management;

import common.cases.CommonScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;

import static talkable.access.managment.access.request.AccessRequestPage.PiiAccess.PII;
import static talkable.access.managment.access.request.AccessRequestPage.TermOfAccess.ONE_DAY;

/*Scenario#1
    * 1. Create site#1 under Admin user.
    * 2. Copy link to the site.
    * 3. Login under STAFF user
    * 4. Open the site.
    * 5. Request PII access for 1 day to the site.
    * 6. Verify PII report
    * 7. Verify CSP
    * 8. Verify User and Privileges portal
    *

    * */

public class PiiAccessRequestTesting extends BaseTest {
    private static final String adminUser = "maxim.laba+auto.admin." + TestDataGenerator.getRandomId() + "@talkable.com";
    private static final String staffUser = PropertyLoader.loadProperty("talkable.user.access.management.staff");
    private static final String pswrd = EnvFactory.getPassword();
    private String siteUrl;


    /*Scenario1*/
    @Test
    public void requestPiiAccessForOneDay() {
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.loginAndCreateNewSite(adminUser, pswrd);
        siteUrl = CommonScenarios.getCurrentUrl();
        CommonScenarios.logout();
        CommonScenarios.login(staffUser, pswrd);
        CommonScenarios.navigateToUrl(siteUrl);
        CommonScenarios.requestAccessWithAutoApproval(ONE_DAY, PII);
        //verify some report with PII data:
        ReportsScenarios.openRewardsReport();
        //verify Access to CSP
//        CommonScenarios.openCustomerServicePortal();  //step commented due to investigation of the issue with old CSP
        //verify access to Users & Privileges
        CommonScenarios.openUsersAndPrivilegesPage();
    }

}