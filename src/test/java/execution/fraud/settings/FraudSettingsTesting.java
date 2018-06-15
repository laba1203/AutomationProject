package execution.fraud.settings;

import api.objects.Site;
import api.scenarios.ViaAPI;
import common.cases.CommonScenarios;
import common.cases.functionalities.FraudRulesScenarios;
import common.cases.functionalities.ReportsScenarios;
import execution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import talkable.talkableSite.fraud.settings.pageData.FraudRulesExpectedTextData;
import util.EnvFactory;
import util.PropertyLoader;
import util.TestDataGenerator;
import util.logging.Log;

import static talkable.talkableSite.fraud.settings.FraudSettingsPage.ApprovalMode.AUTOMATIC;
import static talkable.talkableSite.fraud.settings.FraudSettingsPage.ApprovalMode.MANUAL;
import static talkable.talkableSite.fraud.settings.FraudSettingsPage.ProfileType.*;

public class FraudSettingsTesting extends BaseTest{

    private Site site;

    @BeforeGroups("api-usage")
    public void getApiValues(){
        site = CommonScenarios.getSiteIntegrationValues();
    }

    @Test
    public void login(){
        CommonScenarios.acceptCookiesUsage();
        CommonScenarios.login(
                PropertyLoader.loadProperty("talkable.user.fraudRules"),
                EnvFactory.getPassword());
    }

    @Test(dependsOnMethods = "login")
    public void setHighFraudSettingsProfile(){
        FraudRulesScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                HIGH,
                "Fraud Profile: High",
                FraudRulesExpectedTextData.highProfileDescription
        );
        FraudRulesScenarios.assertValuesInRulesForAdvocateSection(
                "Block Advocate",
                "Block Advocate",
                "Block Advocate",
                "Block Advocate",
                "Skip",
                "Block Advocate"
        );

        FraudRulesScenarios.assertValuesInRulesForFriendSection(
                "Block Friend",
                "Block Friend",
                "Skip",
                "Block Friend",
                "Block Friend"
        );

    }

    @Test(dependsOnMethods = "login")
    public void setElevatedFraudSettingsProfile(){
        FraudRulesScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                ELEVATED,
                "Fraud Profile: Elevated",
                FraudRulesExpectedTextData.elevatedProfileDescription
        );
        FraudRulesScenarios.assertValuesInRulesForAdvocateSection(
                "Block Advocate",
                "Flag Advocate",
                "Flag Advocate",
                "Flag Advocate",
                "Skip",
                "Block Advocate"
        );
        FraudRulesScenarios.assertValuesInRulesForFriendSection(
                "Block Friend",
                "Block Friend",
                "Skip",
                "Block Friend",
                "Block Friend"
        );

    }


    @Test(dependsOnMethods = "login")
    public void setModerateFraudSettingsProfile(){
        FraudRulesScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                MODERATE,
                "Fraud Profile: Moderate",
                FraudRulesExpectedTextData.moderateProfileDescription
        );

        FraudRulesScenarios.assertValuesInRulesForAdvocateSection(
                "Block Advocate",
                "Flag Advocate",
                "Flag Advocate",
                "Flag Advocate",
                "Skip",
                "Flag Advocate"
        );

        FraudRulesScenarios.assertValuesInRulesForFriendSection(
                "Block Friend",
                "Skip",
                "Skip",
                "Skip",
                "Skip"
        );
    }

    @Test(dependsOnMethods = "login")
    public void setLowFraudSettingsProfile(){
        FraudRulesScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                LOW,
                "Fraud Profile: Low",
                FraudRulesExpectedTextData.lowProfileDescription
        );
        FraudRulesScenarios.assertValuesInRulesForAdvocateSection(
                "Block Advocate",
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Skip"
        );

        FraudRulesScenarios.assertValuesInRulesForFriendSection(
                "Block Friend",
                "Skip",
                "Skip",
                "Skip",
                "Skip"
        );
    }

    @Test(dependsOnMethods = "login")
    public void setCustomFraudSettingsProfile(){
        FraudRulesScenarios.setFraudProfileAndAssertProfileNameAndDescription(
                CUSTOM,
                "Fraud Profile: Custom",
                FraudRulesExpectedTextData.customProfileDescription
        );
    }


    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void automaticReferralApprovalMode(){
        String advocateEmail = "advocate" + TestDataGenerator.getRandomId() + "@gmail.com";

        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setReferralApprovalModeOnFraudSetting(AUTOMATIC);
        FraudRulesScenarios.setReferralApprovalDelay(0);
        try {
            ViaAPI.createReferral(
                    site,
                    advocateEmail,
                    "friend" + TestDataGenerator.getRandomId() + "@gmail.com");
        }catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, advocateEmail, "friend" + TestDataGenerator.getRandomId() + "@gmail.com");
            Log.logRecord("Referral created from the second attempt");
        }

        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(advocateEmail);

        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Approved",
                "FAILED: Incorrect referral status (advocate email = <" + advocateEmail + ">)."
        );
    }

    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void manualReferralApprovalMode_ApproveReferral(){
        String advocateEmail = "advocate" + TestDataGenerator.getRandomId() + "@gmail.com";

        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setReferralApprovalModeOnFraudSetting(MANUAL);
        try {
            ViaAPI.createReferral(
                    site,
                    advocateEmail,
                    "friend" + TestDataGenerator.getRandomId() + "@gmail.com"
            );
        }catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, advocateEmail, "friend" + TestDataGenerator.getRandomId() + "@gmail.com");
            Log.logRecord("Referral created from the second attempt");
        }
        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(advocateEmail);
        ReportsScenarios.approveFirstRowInReferralsReport();
        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Approved",
                "FAILED: Incorrect referral status(advocate email = <" + advocateEmail + ">)."
        );
    }

    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void manualReferralApprovalMode_VoidReferral(){
        String advocateEmail = "advocate" + TestDataGenerator.getRandomId() + "@gmail.com";

        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setReferralApprovalModeOnFraudSetting(MANUAL);
        try {
            ViaAPI.createReferral(
                    site,
                    advocateEmail,
                    "friend" + TestDataGenerator.getRandomId() + "@gmail.com"
            );
        }catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, advocateEmail, "friend" + TestDataGenerator.getRandomId() + "@gmail.com");
            Log.logRecord("Referral created from the second attempt");
        }
        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(advocateEmail);
        ReportsScenarios.voidFirstRowInReferralsReport();
        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Voided",
                "FAILED: Incorrect referral status"
        );
    }


    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void verifyMatchingByIpForAdvocate(){
        String advocateEmail = "advocate" + TestDataGenerator.getRandomId() + "@gmail.com";
        String friendEmail = "friend" + TestDataGenerator.getRandomId() + "@gmail.com";
        String ipAddress = "91.90.23.94";
        String advocateUUID = ViaAPI.getRandomUUID();

        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setFraudSettingsProfile(CUSTOM);
        FraudRulesScenarios.setAdvocateRules(
                "Skip",
                "Skip",
                "Skip",
                "Block Advocate",
                "Block Advocate",
                "Skip");

        FraudRulesScenarios.setFriendRules(
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Skip");

        ViaAPI.createReferral(site, advocateEmail, friendEmail, advocateUUID, ipAddress, ipAddress);

        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(advocateEmail);

        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Blocked",
                "FAILED: Incorrect referral status (advocate email = <" + advocateEmail + ">)."
        );

        Log.testPassed("Referral is blocked by IP address only");
    }


    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void verifyMatchingByEmailForAdvocateAndFriend() {
        String advocateEmail = "some.fraud.user." + TestDataGenerator.getRandomId() + "@gmail.com";
        String friendEmail = "some.fraud.user." + TestDataGenerator.getRandomId() + "@gmail.com";

        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setFraudSettingsProfile(CUSTOM);
        FraudRulesScenarios.setAdvocateRules(
                "Skip",
                "Block Advocate",
                "Skip",
                "Skip",
                "Skip",
                "Skip");

        FraudRulesScenarios.setFriendRules(
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Skip");

        try {
            ViaAPI.createReferral(site, advocateEmail, friendEmail);
        }catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, advocateEmail, friendEmail);
            Log.logRecord("Referral created from the second attempt");
        }

        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(advocateEmail);

        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Blocked",
                "FAILED: Incorrect referral status (advocate email = <" + advocateEmail + ">)."
        );

        Log.testPassed("Friend Reward was not given because of similar IP address");
    }

    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void verifyCrossReferralDetectionFroAdvocate(){
        String user1 = "user1." + TestDataGenerator.getRandomId() + "@gmail.com";
        String user2 = "user2." + TestDataGenerator.getRandomId() + "@gmail.com";

        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setFraudSettingsProfile(CUSTOM);
        FraudRulesScenarios.setAdvocateRules(
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Block Advocate");

        FraudRulesScenarios.setFriendRules(
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Block Friend");

        try {
            ViaAPI.createReferral(site, user1, user2);
        }catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, user1, user2);
            Log.logRecord("Referral created from the second attempt");
        }
        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(user1);
        try {
            ViaAPI.createReferral(site, user2, user1);
        }catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, user2, user1);
            Log.logRecord("Referral created from the second attempt");
        }
        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(user2);

        Assert.assertEquals(
                ReportsScenarios.getFirstRowStatusFromReferralReport(),
                "Blocked",
                "FAILED: Incorrect referral status (advocate email = <" + user2 + ">). Referral is not blocked for Advocate Cross Referral rule."
        );

        Log.testPassed("Referral blocked by Advocate Cross Referral rule.");
    }





    @Test(groups = "api-usage", dependsOnMethods = "login")
    public void limitOfAdvocateRewards(){
        String advocateEmail = "advocate.auto+" + TestDataGenerator.getRandomId() + "@gmail.com";

        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setAdvocateLimitReferralRewards("2");

        //Create 3 referrals for the same AD:
        try {
            ViaAPI.createReferral(site, advocateEmail, "friend1" + TestDataGenerator.getRandomId() + "@gmail.com");
        }catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, advocateEmail, "friend1" + TestDataGenerator.getRandomId() + "@gmail.com");
            Log.logRecord("Referral created from the second attempt");
        }

        try {
            ViaAPI.createReferral(site, advocateEmail, "friend2" + TestDataGenerator.getRandomId() + "@gmail.com");
        } catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, advocateEmail, "friend2" + TestDataGenerator.getRandomId() + "@gmail.com");
            Log.logRecord("Referral created from the second attempt");
        }

        try {
            ViaAPI.createReferral(site, advocateEmail, "friend3" + TestDataGenerator.getRandomId() + "@gmail.com");
        }catch (AssertionError e){
            Log.logRecord("Failed to create referral due to AssertionError: " + e.getMessage());
            ViaAPI.createReferral(site, advocateEmail, "friend3" + TestDataGenerator.getRandomId() + "@gmail.com");
            Log.logRecord("Referral created from the second attempt");
        }


        //Verify values in Referral report:
        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(advocateEmail);
        Assert.assertEquals(
                ReportsScenarios.getAdvocateUnpaidReasonFromTheFirstRow(),
                "Advocate has reached maximum rewards",
                "FAILED: Incorrect Advocate reward unpaid reason in the Referral Report (Advocate email = <" + advocateEmail + ">)."
        );

    }


    /*
     * Scenario for Matching By Ip Address For Friend should be removed or reworked as it doesn't work as it designed now.

    @Test(groups = "api-usage", dependsOnMethods = "login"
            ,expectedExceptions = java.lang.AssertionError.class) //Expected exception has been added for bug investigation
    public void verifyMatchingByIpForFriend(){
        String advocateEmail = "advocate" + TestDataGenerator.getRandomId() + "@gmail.com";
        String friendEmail = "friend" + TestDataGenerator.getRandomId() + "@gmail.com";
        String ipAddress = "91.90.23.94";
        String advocateUUID = ViaAPI.getRandomUUID();

        FraudRulesScenarios.openFraudSettings();
        FraudRulesScenarios.setFraudSettingsProfile(CUSTOM);
        FraudRulesScenarios.setAdvocateRules(
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Skip",
                "Skip");

        FraudRulesScenarios.setFriendRules(
                "Skip",
                "Block Friend",
                "Block Friend",
                "Skip",
                "Skip");

        ViaAPI.createReferral(site, advocateEmail, friendEmail, advocateUUID, ipAddress, ipAddress);

        ReportsScenarios.openReferralsReport();
        ReportsScenarios.assertThatReferralCreatedForTheAdvocate(advocateEmail);

        Assert.assertEquals(
                ReportsScenarios.getFriendUnpaidReasonFromTheFirstRow(),
                "Campaign doesn't offer Self-Referral Incentive",
                "FAILED: Incorrect Friend reward unpaid reason in the Referral Report (Advocate email = <" + advocateEmail + ">)."
        );

        Log.testPassed("Friend Reward was not given because of similar IP address");
    }*/









}
