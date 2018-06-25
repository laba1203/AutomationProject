package execution.csp;

import api.objects.Site;
import api.scenarios.ViaAPI;
import common.cases.CommonScenarios;
import common.cases.functionalities.CspScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;

    /*
    * Scenarios#1: Verify values on Person Lookup.
    *
    * 1. Make referral via API
    * 2. Search advocate
    * 3. Click details for the referral
    * 4. Verify paid AD status
    * 5. Make purchase under AD (via API) --> Purchase count increased
    * 6. Verify count of the purchases in the tab
    *
    *
    * Scenario#2: Approve pending referral.
    *
    * 1. Make referral via API
    * 2. Open CSP --> Pending referrals
    * 3. Approve referral
    * 4. Open Person Lookup
    * 5. Search advocate.
    * 6. Verify referral status.
    *
    *
    * Scenario#3: Void pending referral.
    *
    * 1. Make referral via API
    * 2. Open CSP --> Pending referrals
    * 3. Void referral
    * 4. Open Person Lookup
    * 5. Search advocate.
    * 6. Verify referral status.
    *
    *
    * Scenario#4: Blacklist by Email
    *
    * Scenario#5: Anonymize person
    *
    * */


public class CspTesting extends BaseTest{

    private Site site;

    @BeforeClass
    public void login(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(
                PropertyLoader.loadProperty("talkable.user.csp"),
                EnvFactory.getPassword()
        );
        site = CommonScenarios.getSiteIntegrationValues();
    }


    /*Scenarios#1
    * */
    @Test
    public void verifyPersonLookupPage(){
        String advocate = "advocate.auto+" + TestDataGenerator.getRandomId() + "@gmail.com";
        String friend = "friend.auto+" + TestDataGenerator.getRandomId() + "@gmail.com";

        ViaAPI.createReferral(site, advocate, friend);
        CommonScenarios.openCustomerServicePortal();
        CspScenarios.openPersonLookupPage();
        CspScenarios.searchPersonLookup(advocate);
        CspScenarios.openReferralDetailsFromPersonLookup(friend);
        Assert.assertEquals(
                CspScenarios.getAdvocateRewardStatusFromReferralDetails(),
                "pending",
                "FAILED: Incorrect Advocate reward status in Person Lookup --> Referral Details. AD email <" + advocate + ">, FR email <" + friend + ">.");

        String purchasesCount = CspScenarios.getPurchasesRowsCountFromPersonLookup();
        Assert.assertEquals(purchasesCount, "1", "FAILED: Incorrect count of purchases");
        ViaAPI.makePurchaseWithRandomUUID(site, advocate);
        CspScenarios.openPurchasesTabOnPersonLookupPage();
        CspScenarios.openReferralsTabOnPersonLookupPage();

//        CspScenarios.waitTillPurchasesRowsCountChanges(purchasesCount);
        purchasesCount = CspScenarios.getPurchasesRowsCountFromPersonLookup();
        Assert.assertEquals(purchasesCount, "2", "FAILED: Incorrect count of purchases");

        Log.testPassed("Person Lookup page smoke scenario.");
    }


    /*Scenario#2
    * */
    @Test
    public void approvePendingReferral(){
        String advocate = "advocate.auto+" + TestDataGenerator.getRandomId() + "@gmail.com";
        String friend = "friend.auto+" + TestDataGenerator.getRandomId() + "@gmail.com";



    }



}
